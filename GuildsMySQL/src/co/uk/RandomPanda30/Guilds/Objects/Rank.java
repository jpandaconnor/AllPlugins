package co.uk.RandomPanda30.Guilds.Objects;

public class Rank {

	private String rankinfo;
	
	private String name;
	private String title;

	private boolean invite = false;
	private boolean inviteguild;
	private boolean kick = false;
	private boolean kickguild;
	private boolean gmotd;
	private boolean gchat;
	private boolean rankset;
	private boolean ranktitle;
	private boolean rankcreate;
	private boolean rankdelete;
	private boolean rankperms;
	private boolean plotcreate;
	private boolean plotdelete;
	private boolean plothome;
	private boolean plotreset;
	private boolean wardeclare;
	private boolean warsurrender;
	private boolean pvp;

	public Rank (String rankInfo) {
		setRankinfo(rankInfo);
		String[] s1 = rankInfo.split(":"); // Getting name here
		name = s1[0];

		String n1 = s1[1]; // Rest of permissions here

		String[] s2 = n1.split("!");

		invite = Boolean.valueOf(s2[0].split(";")[1]);
		inviteguild = Boolean.valueOf(s2[1].split(";")[1]);
		kick = Boolean.valueOf(s2[2].split(";")[1]);
		kickguild = Boolean.valueOf(s2[3].split(";")[1]);
		gmotd = Boolean.valueOf(s2[4].split(";")[1]);
		gchat = Boolean.valueOf(s2[5].split(";")[1]);
		rankset = Boolean.valueOf(s2[6].split(";")[1]);
		ranktitle = Boolean.valueOf(s2[7].split(";")[1]);
		rankcreate = Boolean.valueOf(s2[8].split(";")[1]);
		rankdelete = Boolean.valueOf(s2[9].split(";")[1]);
		rankperms = Boolean.valueOf(s2[10].split(";")[1]);
		plotcreate = Boolean.valueOf(s2[11].split(";")[1]);
		plotdelete = Boolean.valueOf(s2[12].split(";")[1]);
		plothome = Boolean.valueOf(s2[13].split(";")[1]);
		plotreset = Boolean.valueOf(s2[14].split(";")[1]);
		wardeclare = Boolean.valueOf(s2[15].split(";")[1]);
		warsurrender = Boolean.valueOf(s2[16].split(";")[1]);
		title = s2[17].split(";")[1];
		pvp = Boolean.valueOf(s2[18].split(";")[1]);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isInvite() {
		return invite;
	}

	public void setInvite(boolean invite) {
		this.invite = invite;
	}

	public boolean isInviteguild() {
		return inviteguild;
	}

	public void setInviteguid(boolean inviteguild) {
		this.inviteguild = inviteguild;
	}

	public boolean isKick() {
		return kick;
	}

	public void setKick(boolean kick) {
		this.kick = kick;
	}

	public boolean isKickguild() {
		return kickguild;
	}

	public void setKickguild(boolean kickguild) {
		this.kickguild = kickguild;
	}

	public boolean isGmotd() {
		return gmotd;
	}

	public void setGmotd(boolean gmotd) {
		this.gmotd = gmotd;
	}

	public boolean isGchat() {
		return gchat;
	}

	public void setGchat(boolean gchat) {
		this.gchat = gchat;
	}

	public boolean isRankset() {
		return rankset;
	}

	public void setRankset(boolean rankset) {
		this.rankset = rankset;
	}

	public boolean isRanktitle() {
		return ranktitle;
	}

	public void setRanktitle(boolean ranktitle) {
		this.ranktitle = ranktitle;
	}

	public boolean isRankcreate() {
		return rankcreate;
	}

	public void setRankcreate(boolean rankcreate) {
		this.rankcreate = rankcreate;
	}

	public boolean isRankdelete() {
		return rankdelete;
	}

	public void setRankdelete(boolean rankdelete) {
		this.rankdelete = rankdelete;
	}

	public boolean isRankperms() {
		return rankperms;
	}

	public void setRankperms(boolean rankperms) {
		this.rankperms = rankperms;
	}

	public boolean isPlotcreate() {
		return plotcreate;
	}

	public void setPlotcreate(boolean plotcreate) {
		this.plotcreate = plotcreate;
	}

	public boolean isPlotdelete() {
		return plotdelete;
	}

	public void setPlotdelete(boolean plotdelete) {
		this.plotdelete = plotdelete;
	}

	public boolean isPlothome() {
		return plothome;
	}

	public void setPlothome(boolean plothome) {
		this.plothome = plothome;
	}

	public boolean isPlotreset() {
		return plotreset;
	}

	public void setPlotreset(boolean plotreset) {
		this.plotreset = plotreset;
	}

	public boolean isWardeclare() {
		return wardeclare;
	}

	public void setWardeclare(boolean wardeclare) {
		this.wardeclare = wardeclare;
	}

	public boolean isWarsurrender() {
		return warsurrender;
	}

	public void setWarsurrender(boolean warsurrender) {
		this.warsurrender = warsurrender;
	}

	public boolean isPvp() {
		return pvp;
	}

	public void setPvp(boolean pvp) {
		this.pvp = pvp;
	}

	public String getRankinfo() {
		return rankinfo;
	}

	public void setRankinfo(String rankinfo) {
		this.rankinfo = rankinfo;
	}
}