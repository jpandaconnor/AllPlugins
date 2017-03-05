package co.uk.randompanda30.fileguilds.plot;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlotsGenerator extends ChunkGenerator {

    public final int PLOT_HEIGHT = 64;
    public final int PLOT_WIDTH = 100;
    public final int ROAD_WIDTH = 600;

    public final short ROAD_BLOCK = 0;
    public final short MAIN_BLOCK = 1;
    public final short WALL_BLOCK = 0;
    public final short BORDER_BLOCK = 0;
    public final short[] FLOOR_BLOCK = new short[]{2};

    public final PseudoRandom RANDOM = new PseudoRandom();
    public final int total_width;
    public final int road_width_lower;
    public final int road_width_upper;
    public short[][][] CACHE_I = null;
    public short[][][] CACHE_J = null;
    private short[][] base_result;

    @SuppressWarnings("unused")
    public PlotsGenerator() {
        initCache();
        total_width = PLOT_WIDTH + ROAD_WIDTH;

        if ((ROAD_WIDTH % 2) == 0) {
            road_width_lower = ROAD_WIDTH / 2 - 1;
        } else {
            road_width_lower = ROAD_WIDTH / 2;
        }

        road_width_upper = road_width_lower + PLOT_WIDTH + 1;

        base_result = new short[1 + PLOT_HEIGHT / 16][];
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                setBlock(base_result, x, 0, z, (short) 7);
            }
        }
    }

    public void initCache() {
        if (CACHE_I == null) {
            CACHE_I = new short[256][16][16];
            CACHE_J = new short[256][16][16];
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y <= PLOT_HEIGHT; y++) {
                        short i = (short) (y >> 4);
                        short j = (short) (((y & 0xF) << 8) | (z << 4) | x);
                        CACHE_I[y][x][z] = i;
                        CACHE_J[y][x][z] = j;
                    }
                }
            }
        }
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        world.setSpawnFlags(false, false);
        world.setAmbientSpawnLimit(0);
        world.setAnimalSpawnLimit(0);
        world.setMonsterSpawnLimit(0);
        world.setWaterAnimalSpawnLimit(0);
        return new ArrayList<>();
    }

    @Override
    public short[][] generateExtBlockSections(World world, Random r, int cx,
                                              int cz, BiomeGrid biomes) {
        short[][] result = new short[1 + PLOT_HEIGHT / 16][];
        try {
            RANDOM.state = pair(cx, cz);

            result[0] = new short[4096];
            System.arraycopy(base_result[0], 0, result[0], 0,
                    base_result[0].length);
            int bx = (cx << 4) % total_width + (cx < 0 ? total_width : 0);
            int bz = (cz << 4) % total_width + (cz < 0 ? total_width : 0);

            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    biomes.setBiome(x, z, Biome.FOREST);
                    int xx = (x + bx);
                    int zz = (z + bz);

                    if (xx >= total_width)
                        xx -= total_width;

                    if (zz >= total_width)
                        zz -= total_width;

                    if (xx < road_width_lower || zz < road_width_lower
                            || xx > road_width_upper || zz > road_width_upper) {
                        for (int y = 0; y < PLOT_HEIGHT; y++)
                            setBlock(result, x, y, z, ROAD_BLOCK);
                    } else if (xx == road_width_lower || zz == road_width_lower
                            || xx == road_width_upper
                            || zz == road_width_upper) {
                        for (int y = 0; y < PLOT_HEIGHT; y++)
                            setBlock(result, x, y, z, WALL_BLOCK);
                        setBlock(result, x, PLOT_HEIGHT, z, BORDER_BLOCK);
                    } else {
                        for (int y = 0; y < PLOT_HEIGHT - 1; y++)
                            setBlock(result, x, y, z, MAIN_BLOCK);
                        setBlock(result, x, PLOT_HEIGHT - 1, z, FLOOR_BLOCK);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int pair(int x, int y) {
        long hash;
        if (x >= 0) {
            if (y >= 0) {
                hash = (x * x) + (3 * x) + (2 * x * y) + y + (y * y) + 2;
            } else {
                final int y1 = -y;
                hash = (x * x) + (3 * x) + (2 * x * y1) + y1 + (y1 * y1) + 1;
            }
        } else {
            final int x1 = -x;
            if (y >= 0) {
                hash = -((x1 * x1) + (3 * x1) + (2 * x1 * y) + y + (y * y));
            } else {
                final int y1 = -y;
                hash = -((x1 * x1) + (3 * x1) + (2 * x1 * y1) + y1 + (y1 * y1)
                        + 1);
            }
        }
        return (int) (hash % Integer.MAX_VALUE);
    }

    public void setBlock(final short[][] result, final int x, final int y,
                         final int z, final short blkid) {
        if (result[CACHE_I[y][x][z]] == null) {
            result[CACHE_I[y][x][z]] = new short[4096];
        }
        result[CACHE_I[y][x][z]][CACHE_J[y][x][z]] = blkid;
    }

    public void setBlock(final short[][] result, final int x, final int y,
                         final int z, final short[] blkid) {
        if (blkid.length == 1) {
            setBlock(result, x, y, z, blkid[0]);
        }

        short id = blkid[RANDOM.random(blkid.length)];
        if (result[CACHE_I[y][x][z]] == null) {
            result[CACHE_I[y][x][z]] = new short[4096];
        }
        result[CACHE_I[y][x][z]][CACHE_J[y][x][z]] = id;
    }
}