import java.io.*;
import java.util.*;

class Person {
    // attributes
    public String id;
    public String name;
    public String age;
    public String job;
    public String address;

    Person(String id, String name, String age, String job, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.job = job;
        this.address = address;

    }

    public String getUserData() {
        return id + "," + name + "," + age + "," + job + "," + address;
    }
}

public class ayush {
    public static String filename = "ayush.csv";

    // main method
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        createfile();
        // menu
        while (true) {
            System.out.println("please choose any service");
            System.out.println("===========================");
            System.out.println("press 1 to add new person");
            System.out.println("press 2 to delete  person");
            System.out.println("press 3 to update  person");
            System.out.println("press 4 to get all persons ");
            System.out.println("press 0 to Exit");
            System.out.println("===========================");
            int x = sc.nextInt();
            if (x == 0) {
                break;
            } else if (x == 1) {
                add();
            } else if (x == 2) {
                delete(arrayList);
            } else if (x == 3) {
                update(arrayList);
            } else if (x == 4) {
                show();
            } else {
                System.out.println("please enter a vaild number");
            }
        }
    }

    public static void add() {
        // add local scanner object
        Scanner sc1 = new Scanner(System.in);

        System.out.println("In add ");

        System.out.println("Please Enter Id  ");
        String id = sc1.next();
        // id += "$" + new Random().nextInt(100);
        System.out.println("please enter your name");
        String name = sc1.next();
        System.out.println("Please Enter your age  ");
        String age = sc1.next();
        System.out.println("please enter your job");
        String job = sc1.next();
        System.out.println("please enter your address");
        String address = sc1.next();
        Person person = new Person(id, name, age, job, address);

        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.append(person.getUserData());
            writer.append("\n");
            writer.close();
            System.out.println("person added succefully!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void delete(ArrayList<String> arrayList) {
        Scanner sc_de = new Scanner(System.in);
        System.out.println("enter name to delete a record");
        String searchKey = sc_de.next();
        String line;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchKey)) {
                    System.out.println(line);
                    continue;
                } else {
                    arrayList.add(line);
                }
            }

        } catch (Exception e) {
        }
        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < arrayList.size(); i++) {
                writer.append(arrayList.get(i));
                writer.append("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("done!");
        }
    }

    public static void update(ArrayList<String> arrayList) {
        Scanner up_sc=new Scanner(System.in);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            System.out.println("please enter Name to update the record");
            String searchKey = up_sc.next();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchKey)) {
                    System.out.println("enter the old text you want to change");
                    String oldValue = up_sc.next();
                    System.out.println("enter the new text you want to change");
                    String newValue = up_sc.next();
                    arrayList.add(line.replace(oldValue, newValue));
                } else {
                    arrayList.add(line);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < arrayList.size(); i++) {
                writer.append(arrayList.get(i));
                writer.append("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void show() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createfile() {
        File database = new File(filename);

        try {

            if (database.createNewFile() == true) {

                try {
                    FileWriter writer = new FileWriter(filename, true);
                    writer.append("User ID" + "," + "User Name" + "," + "Age" + "," + "Job" + "," + "Address");
                    writer.append("\n");
                    writer.close();
                    System.out.println("file created succefully!");
                } catch (IOException e) {
                    System.out.println(e);
                }

            } else {
                System.out.println("File is all redy exist");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}