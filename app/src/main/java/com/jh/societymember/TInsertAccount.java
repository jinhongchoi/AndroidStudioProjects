package com.jh.societymember;

public class TInsertAccount {

    private String TeamName;
    private String Region;
    private String Sports;
    private String Leader;

    public TInsertAccount() { // 기본 생성자
    }

    public TInsertAccount(String teamName, String region, String sports, String leader) {
        TeamName = teamName;
        Region = region;
        Sports = sports;
        Leader = leader;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getSports() {
        return Sports;
    }

    public void setSports(String sports) {
        Sports = sports;
    }

    public String getLeader() {
        return Leader;
    }

    public void setLeader(String leader) {
        Leader = leader;
    }

    @Override
    public String toString() {
        return "TInsertAccount{" +
                "TeamName='" + TeamName + '\'' +
                ", Region='" + Region + '\'' +
                ", Sports='" + Sports + '\'' +
                ", Leader='" + Leader + '\'' +
                '}';
    }
}
