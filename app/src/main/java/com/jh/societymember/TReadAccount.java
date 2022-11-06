package com.jh.societymember;

public class TReadAccount {
    public String TeamName;
    public String Region;
    public String Sports;
    public String Leader;

    public TReadAccount(){

    }

    public TReadAccount(String teamName, String region, String sports, String leader) {
        this.TeamName = teamName;
        this.Region = region;
        this.Sports = sports;
        this.Leader = leader;
    }
    

    @Override
    public String toString() {
        return "TReadAccount{" +
                "TeamName='" + TeamName + '\'' +
                ", Region='" + Region + '\'' +
                ", Sports='" + Sports + '\'' +
                ", Leader='" + Leader + '\'' +
                '}';
    }
}
