import java.util.*;

class Student implements Comparable<Student> {

    private String name;
    private int kor;
    private int eng;
    private int m;

    public Student(String name, int kor, int eng, int m) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.m = m;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Student other) {
        if (this.kor == other.kor && this.eng == other.eng && this.m == other.m) {
            return this.name.compareTo(other.name);
        }
        if (this.kor == other.kor && this.eng == other.eng) {
            return Integer.compare(other.m, this.m);
        }
        if (this.kor == other.kor) {
            return Integer.compare(this.eng, other.eng);
        }
        return Integer.compare(other.kor, this.kor);
    }
}

public class sort_국영수 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int kor = sc.nextInt();
            int eng = sc.nextInt();
            int m = sc.nextInt();
            students.add(new Student(name, kor, eng, m));
        }

        Collections.sort(students);

        for (int i = 0; i < n; i++) {
            System.out.println(students.get(i).getName());
        }
    }
}
