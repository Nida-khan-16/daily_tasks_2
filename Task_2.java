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


