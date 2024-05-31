package manager.java.sql.connect.test;

import manager.java.sql.connect.SQLconnect;

public class Run {
    public static void main(String[] args){
        SQLconnect SQL = new SQLconnect();
//        SQL.createUser("Tran Thi Tuong Van", 20, 12345678, "hehehe", "12345");
//        SQL.createUser("Pham Minh Tien", 20, 12345678, "hahaha", "12345" );

//        SQL.createJob("Host",1, 100);
//        SQL.createOrg(1, 1, "Ecec", 1 );
//        SQL.createEvent(1, 1, "Birthday",
//               "2008-10-29 14:56:59", "Hanoi",
//               "app", 20, 50, 20);
        SQL.requestUser(2,1,1,0,0);
    }
}
