package 정렬;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 성적낮은순서 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String name = scanner.next();
            int point = scanner.nextInt();
            Student student = new Student(name, point);
            students.add(student);
        }

        Collections.sort(students);
        for (Student student : students) {
            System.out.println(student.name);
        }
    }

    static class Student implements Comparable<Student> {
        String name;
        int point;

        public Student(final String name, final int point) {
            this.name = name;
            this.point = point;
        }


        @Override
        public int compareTo(final Student nextStudent) {
            if (this.point == nextStudent.point){
                return 0;
            }
            else if (point > nextStudent.point){
                return 1;
            }
            else {
                return -1;
            }
        }
    }
}
