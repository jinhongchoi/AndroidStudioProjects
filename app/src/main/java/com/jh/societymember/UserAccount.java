package com.jh.societymember;


/*
    사용자 계정 정보 모델 클래스 -> VO
*/

public class UserAccount {

    private String idToken; //Firebase Uid(고유 토큰 정보)
    private String emailId;
    private String password;
    private String Name;
    private String Gender;
    private int Age;
    private String Member;



    public UserAccount() { // 기본 생성자
    }

    // alt +insert 할 것!

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getMember() {
        return Member;
    }

    public void setMember(String member) {
        Member = member;
    }

    public UserAccount(String idToken, String emailId, String password, String name, String gender, int age, String member) {
        this.idToken = idToken;
        this.emailId = emailId;
        this.password = password;
        Name = name;
        Gender = gender;
        Age = age;
        Member = member;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "idToken='" + idToken + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", Name='" + Name + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Age=" + Age +
                ", Member='" + Member + '\'' +
                '}';
    }
}
