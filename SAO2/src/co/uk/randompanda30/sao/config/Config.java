package co.uk.randompanda30.sao.config;

/* 
   Created by panda on 17/08/16.
   
   Copyright 2016 JPanda (Connor Brady)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

import co.uk.randompanda30.sao.SAO;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static co.uk.randompanda30.sao.TEMP.*;

public class Config {

    public Config() {
        try {
            config = new File("plugins/" + SAO.getPlugin().getName() + "/config.yml");
            if (!config.exists()) {
                config.getParentFile().mkdirs();
                config.createNewFile();
            }

            configf = new YamlConfiguration();
            configc = configf.getConfigurationSection("");
            configf.load(config);

            for (ConfigValues value : ConfigValues.values()) {
                if (configc.get(value.name().replaceAll("_", ".")) == null) {
                    configc.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }
                value.value = configc.get(value.name().replaceAll("_", "."));
            }

            configf.load(config);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            configf.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum ConfigValues {
        AUTOSHUTDOWN_SENDTOBUNGEE(true),

        BACKUP_PATHTOBACKUP("changeme"),
        BACKUP_PATHTOSTORE("changeme"),
        BACKUP_TOSTORELOGS("changeme"),
        BACKUP_AUTOMATICMODE(true),

        SELECTLIMIT(10000),

        BACKUP_EXCLUSIONPATHS(new ArrayList<String>() {
            {
                add("dynmap/");
            }
        }),

        BACKUP_DELAY("12h"),

        // http://fffff.at/googles-official-list-of-bad-words/

        FORBIDDEN_WORDS(new ArrayList<String>() {
            {
                add("4r5e");
                add("5h1t");
                add("5hit");
                add("a55");
                add("anal");
                add("anus");
                add("ar5e");
                add("arrse");
                add("arse");
                add("ass");
                add("ass-fucker");
                add("asses");
                add("assfucker");
                add("assfukka");
                add("asshole");
                add("assholes");
                add("asswhole");
                add("a_s_s");
                add("b!tch");
                add("b00bs");
                add("b17ch");
                add("b1tch");
                add("ballbag");
                add("balls");
                add("ballsack");
                add("bastard");
                add("beastial");
                add("beastiality");
                add("bellend");
                add("bestial");
                add("bestiality");
                add("bi+ch");
                add("biatch");
                add("bitch");
                add("bitcher");
                add("bitchers");
                add("bitches");
                add("bitchin");
                add("bitching");
                add("bloody");
                add("blow job");
                add("blowjob");
                add("blowjobs");
                add("boiolas");
                add("bollock");
                add("bollok");
                add("boner");
                add("boob");
                add("boobs");
                add("booobs");
                add("boooobs");
                add("booooobs");
                add("booooooobs");
                add("breasts");
                add("buceta");
                add("bugger");
                add("bum");
                add("bunny fucker");
                add("butt");
                add("butthole");
                add("buttmuch");
                add("buttplug");
                add("c0ck");
                add("c0cksucker");
                add("carpet muncher");
                add("cawk");
                add("chink");
                add("cipa");
                add("cl1t");
                add("clit");
                add("clitoris");
                add("clits");
                add("cnut");
                add("cock");
                add("cock-sucker");
                add("cockface");
                add("cockhead");
                add("cockmunch");
                add("cockmuncher");
                add("cocks");
                add("cocksuck ");
                add("cocksucked ");
                add("cocksucker");
                add("cocksucking");
                add("cocksucks ");
                add("cocksuka");
                add("cocksukka");
                add("cok");
                add("cokmuncher");
                add("coksucka");
                add("coon");
                add("cox");
                add("crap");
                add("cum");
                add("cummer");
                add("cumming");
                add("cums");
                add("cumshot");
                add("cunilingus");
                add("cunillingus");
                add("cunnilingus");
                add("cunt");
                add("cuntlick ");
                add("cuntlicker ");
                add("cuntlicking ");
                add("cunts");
                add("cyalis");
                add("cyberfuc");
                add("cyberfuck ");
                add("cyberfucked ");
                add("cyberfucker");
                add("cyberfuckers");
                add("cyberfucking ");
                add("d1ck");
                add("dick");
                add("dickhead");
                add("dildo");
                add("dildos");
                add("dink");
                add("dinks");
                add("dirsa");
                add("dlck");
                add("dog-fucker");
                add("doggin");
                add("dogging");
                add("donkeyribber");
                add("doosh");
                add("duche");
                add("dyke");
                add("ejaculate");
                add("ejaculated");
                add("ejaculates ");
                add("ejaculating ");
                add("ejaculatings");
                add("ejaculation");
                add("ejakulate");
                add("f u c k");
                add("f u c k e r");
                add("f4nny");
                add("fag");
                add("fagging");
                add("faggitt");
                add("faggot");
                add("faggs");
                add("fagot");
                add("fagots");
                add("fags");
                add("fanny");
                add("fannyflaps");
                add("fannyfucker");
                add("fanyy");
                add("fatass");
                add("fcuk");
                add("fcuker");
                add("fcuking");
                add("feck");
                add("fecker");
                add("felching");
                add("fellate");
                add("fellatio");
                add("fingerfuck ");
                add("fingerfucked ");
                add("fingerfucker ");
                add("fingerfuckers");
                add("fingerfucking ");
                add("fingerfucks ");
                add("fistfuck");
                add("fistfucked ");
                add("fistfucker ");
                add("fistfuckers ");
                add("fistfucking ");
                add("fistfuckings ");
                add("fistfucks ");
                add("flange");
                add("fook");
                add("fooker");
                add("fuck");
                add("fucka");
                add("fucked");
                add("fucker");
                add("fuckers");
                add("fuckhead");
                add("fuckheads");
                add("fuckin");
                add("fucking");
                add("fuckings");
                add("fuckingshitmotherfucker");
                add("fuckme ");
                add("fucks");
                add("fuckwhit");
                add("fuckwit");
                add("fudge packer");
                add("fudgepacker");
                add("fuk");
                add("fuker");
                add("fukker");
                add("fukkin");
                add("fuks");
                add("fukwhit");
                add("fukwit");
                add("fux");
                add("fux0r");
                add("f_u_c_k");
                add("gangbang");
                add("gangbanged ");
                add("gangbangs ");
                add("gaylord");
                add("gaysex");
                add("goatse");
                add("God");
                add("god-dam");
                add("god-damned");
                add("goddamn");
                add("goddamned");
                add("hardcoresex ");
                add("hell");
                add("heshe");
                add("hoar");
                add("hoare");
                add("hoer");
                add("homo");
                add("hore");
                add("horniest");
                add("horny");
                add("hotsex");
                add("jack-off ");
                add("jackoff");
                add("jap");
                add("jerk-off ");
                add("jism");
                add("jiz ");
                add("jizm ");
                add("jizz");
                add("kawk");
                add("knob");
                add("knobead");
                add("knobed");
                add("knobend");
                add("knobhead");
                add("knobjocky");
                add("knobjokey");
                add("kock");
                add("kondum");
                add("kondums");
                add("kum");
                add("kummer");
                add("kumming");
                add("kums");
                add("kunilingus");
                add("l3i+ch");
                add("l3itch");
                add("labia");
                add("lmfao");
                add("lust");
                add("lusting");
                add("m0f0");
                add("m0fo");
                add("m45terbate");
                add("ma5terb8");
                add("ma5terbate");
                add("masochist");
                add("master-bate");
                add("masterb8");
                add("masterbat*");
                add("masterbat3");
                add("masterbate");
                add("masterbation");
                add("masterbations");
                add("masturbate");
                add("mo-fo");
                add("mof0");
                add("mofo");
                add("mothafuck");
                add("mothafucka");
                add("mothafuckas");
                add("mothafuckaz");
                add("mothafucked ");
                add("mothafucker");
                add("mothafuckers");
                add("mothafuckin");
                add("mothafucking ");
                add("mothafuckings");
                add("mothafucks");
                add("mother fucker");
                add("motherfuck");
                add("motherfucked");
                add("motherfucker");
                add("motherfuckers");
                add("motherfuckin");
                add("motherfucking");
                add("motherfuckings");
                add("motherfuckka");
                add("motherfucks");
                add("muff");
                add("mutha");
                add("muthafecker");
                add("muthafuckker");
                add("muther");
                add("mutherfucker");
                add("n1gga");
                add("n1gger");
                add("nazi");
                add("nigg3r");
                add("nigg4h");
                add("nigga");
                add("niggah");
                add("niggas");
                add("niggaz");
                add("nigger");
                add("niggers ");
                add("nob");
                add("nob jokey");
                add("nobhead");
                add("nobjocky");
                add("nobjokey");
                add("numbnuts");
                add("nutsack");
                add("orgasim ");
                add("orgasims ");
                add("orgasm");
                add("orgasms ");
                add("p0rn");
                add("pawn");
                add("pecker");
                add("penis");
                add("penisfucker");
                add("phonesex");
                add("phuck");
                add("phuk");
                add("phuked");
                add("phuking");
                add("phukked");
                add("phukking");
                add("phuks");
                add("phuq");
                add("pigfucker");
                add("pimpis");
                add("piss");
                add("pissed");
                add("pisser");
                add("pissers");
                add("pisses ");
                add("pissflaps");
                add("pissin ");
                add("pissing");
                add("pissoff ");
                add("poop");
                add("porn");
                add("porno");
                add("pornography");
                add("pornos");
                add("prick");
                add("pricks ");
                add("pron");
                add("pube");
                add("pusse");
                add("pussi");
                add("pussies");
                add("pussy");
                add("pussys ");
                add("rectum");
                add("retard");
                add("rimjaw");
                add("rimming");
                add("s hit");
                add("s.o.b.");
                add("sadist");
                add("schlong");
                add("screwing");
                add("scroat");
                add("scrote");
                add("scrotum");
                add("semen");
                add("sex");
                add("sh!+");
                add("sh!t");
                add("sh1t");
                add("shag");
                add("shagger");
                add("shaggin");
                add("shagging");
                add("shemale");
                add("shi+");
                add("shit");
                add("shitdick");
                add("shite");
                add("shited");
                add("shitey");
                add("shitfuck");
                add("shitfull");
                add("shithead");
                add("shiting");
                add("shitings");
                add("shits");
                add("shitted");
                add("shitter");
                add("shitters ");
                add("shitting");
                add("shittings");
                add("shitty ");
                add("skank");
                add("slut");
                add("sluts");
                add("smegma");
                add("smut");
                add("snatch");
                add("son-of-a-bitch");
                add("spac");
                add("spunk");
                add("s_h_i_t");
                add("t1tt1e5");
                add("t1tties");
                add("teets");
                add("teez");
                add("testical");
                add("testicle");
                add("tit");
                add("titfuck");
                add("tits");
                add("titt");
                add("tittie5");
                add("tittiefucker");
                add("titties");
                add("tittyfuck");
                add("tittywank");
                add("titwank");
                add("tosser");
                add("turd");
                add("tw4t");
                add("twat");
                add("twathead");
                add("twatty");
                add("twunt");
                add("twunter");
                add("v14gra");
                add("v1gra");
                add("vagina");
                add("viagra");
                add("vulva");
                add("w00se");
                add("wang");
                add("wank");
                add("wanker");
                add("wanky");
                add("whoar");
                add("whore");
                add("willies");
                add("willy");
                add("xrated");
                add("xxx");
            }
        });

        public Object value;

        ConfigValues(Object value) {
            this.value = value;
        }
    }
}