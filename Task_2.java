import java.io.*;
import java.util.*;

// Serializable Student class
class Student implements Serializable {
    private int id;
    private String name;
    private int marks;

    public Student(String name, int id, int marks) {
        this.name = name;
        this.id = id;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getMarks() { return marks; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMarks(int marks) { this.marks = marks; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Marks: " + marks;
    }
}

 class StudentRecordSystem {
    private static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> studentList = loadStudents();

        while (true) {
            System.out.println("\n=== Student Record Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); // clear buffer

            switch (choice) {
                case 1: // Add
                    System.out.print("Enter ID: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter Marks: ");
                    int marks = scan.nextInt();

                    studentList.add(new Student(name, id, marks));
                    saveStudents(studentList);
                    System.out.println("✅ Student added successfully!");
                    break;

                case 2: // View
                    if (studentList.isEmpty()) {
                        System.out.println("⚠️ No students found.");
                    } else {
                        System.out.println("\n--- Student Records ---");
                        for (Student s : studentList) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3: // Update
                    System.out.print("Enter ID of student to update: ");
                    int updateId = scan.nextInt();
                    scan.nextLine();

                    boolean found = false;
                    for (int i = 0; i < studentList.size(); i++) {
                        Student s = studentList.get(i);
                        if (s.getId() == updateId) {
                            System.out.print("Enter new name (press Enter to keep same): ");
                            String newName = scan.nextLine();
                            if (!newName.trim().isEmpty()) s.setName(newName);

                            System.out.print("Enter new marks (-1 to keep same): ");
                            int newMarks = scan.nextInt();
                            if (newMarks != -1) s.setMarks(newMarks);

                            studentList.set(i, s);
                            saveStudents(studentList);
                            System.out.println("✅ Student updated successfully!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("❌ Student not found.");
                    break;

                case 4: // Delete
                    System.out.print("Enter ID of student to delete: ");
                    int deleteId = scan.nextInt();

                    found = false;
                    Iterator<Student> it = studentList.iterator();
                    while (it.hasNext()) {
                        Student s = it.next();
                        if (s.getId() == deleteId) {
                            it.remove();
                            saveStudents(studentList);
                            System.out.println("🗑️ Student deleted successfully!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("❌ Student not found.");
                    break;

                case 5: // Exit
                    System.out.println("👋 Exiting... Goodbye!");
                    scan.close();
                    return;

                default:
                    System.out.println("⚠️ Invalid choice, please try again.");
            }
        }
    }

    // Load students from file
    private static ArrayList<Student> loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Student>) ois.readObject();
        } catch (EOFException | FileNotFoundException e) {
            return new ArrayList<>(); // if file empty/missing → new list
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Save students to file
    private static void saveStudents(ArrayList<Student> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.Serializable;

class student implements Serializable {
    private String name;
    private int id;
    public student(String name, int id){
        
        this.name = name ;
        this.id = id;
    }
     public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
         @Override
        public String toString() {
            return "student [name=" + name + ", id=" + id + "]";
        }
}







public class Task_2 {
     static void saveArrayList(ArrayList<student> list, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
            System.out.println("ArrayList saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<student> loadArrayList(String filename) {
        ArrayList<student> list = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            list = (ArrayList<student>) ois.readObject();
            System.out.println("ArrayList loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args){
    String name="null";
    int index=0;
    int id=0;
    char[] ans;
    char[] ans1;
    int i=id;
        Scanner scan=new Scanner(System.in);
        ArrayList<student> studentlist=new ArrayList<>();
   System.out.println("Hello! welcome to our interace \n ");
   System.out.println("For registration of a new entry type R ");
   System.out.println("For view registrared student type V ");
   System.out.println("For updating entry existing type U");
   System.out.println("for deletion of any entry type D");
   String ansString=scan.next().trim().toLowerCase();
    ans=ansString.toCharArray();
    if(ans[0]=='r'){
        
    do{
    System.out.println("for registration you have to fill some information for record!!");
    System.out.print("Please enter you name : ");
    scan.nextLine();
     name =scan.nextLine();
    System.out.print("Please enter you student ID : ");
     id=scan.nextInt();
   

   studentlist.add(i,new student(name, id));
   
   System.out.println("do you want to add another entry?(Y/N)");
    ansString=scan.next().trim().toLowerCase();
    ans1=ansString.toCharArray();
    i++;
    }
 while(ans1[0]=='y');
saveArrayList(studentlist, "student_data.std");
ArrayList<student> loadedData = loadArrayList("student_data.std");
System.out.println(loadedData);}
 if(ans[0]=='v'){
    ArrayList<student> loadedData = loadArrayList("student_data.std");
    
System.out.println(studentlist.size());

    if (loadedData != null) {
            System.out.println("Loaded data: " + loadedData);
        }
 
    }
    if(ans[0]=='u'){
       // index=studentlist.indexOf(id);
       index=studentlist.size();
        scan.nextLine();
        System.out.print("Please enter new name(write original if don't want to change) : ");
        name =scan.nextLine();
     System.out.print("Please enter you student ID(write original if don't want to change) : ");
     id=scan.nextInt();
     System.out.println(index);
    studentlist.set(id,new student(name, id));
    
    }
if(ans[0]=='d'){
      System.out.println("enter id no. you want to delete");
       id=scan.nextInt();
       System.out.println(studentlist.size());
//studentlist.remove(id);
saveArrayList(studentlist, "student_data.std");
ArrayList<student> loadedData = loadArrayList("student_data.std");
}
    
}
}  */