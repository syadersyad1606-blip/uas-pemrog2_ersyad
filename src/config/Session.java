package config;

public class Session {

    private static String username;
    private static String namaUser;
    private static String role;

    public static void setUsername(String username){
        Session.username = username;
    }

    public static String getUsername(){
        return username;
    }

    public static void setNamaUser(String namaUser){
        Session.namaUser = namaUser;
    }

    public static String getNamaUser(){
        return namaUser;
    }

    public static void setRole(String role){
        Session.role = role;
    }

    public static String getRole(){
        return role;
    }

}