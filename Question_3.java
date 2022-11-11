package assignment_5;

import java.util.Random;
import java.util.Scanner;   
class Marks {
	
	int rollNo;
	String name;
	int marks;
	
	Marks() { // Marks constructor is called initializing the data members
		Random random = new Random();
		marks = random.nextInt(100);  // marks in integer from 0 to 100
	}
	
}

class Physics extends Marks {
	static double sum; // sum adds all the marks of every Physics object 
	
	Physics() {
		super(); // for calling the constructor of the super class Marks
		sum += this.marks; // adding marks of the object to the sum 
	}
}

class Chemistry extends Marks {
	static double sum; // sum adds all the marks of every Chemistry object
	
	Chemistry() {
		super(); // for calling the constructor of the super class Marks
		sum += this.marks; // adding marks of the object to the sum 
	}
}

class Maths extends Marks {
	static double sum; // sum adds all the marks of every Maths object
	
	Maths() {
		super(); // for calling the constructor of the super class Marks
		sum += this.marks; // adding marks of the object to the sum 
	}
	
}

public class Question_3 {
	
	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		System.out.print("Input number of students : ");
		int numOfStudents = scn.nextInt();  // number of students in the class
		
		Physics[] phys_students = new Physics[numOfStudents]; // Physics class array for storing physics students objects
		Chemistry[] chem_students = new Chemistry[numOfStudents]; // Chemistry class array for storing chemistry students objects
		Maths[] maths_students = new Maths[numOfStudents];// Maths class array for storing maths students objects
		
		for(int i=0 ; i<numOfStudents ; i++) {
			phys_students[i] = new Physics(); // initializing the objects
			chem_students[i] = new Chemistry();
		    maths_students[i] = new Maths();
		    
		    phys_students[i].rollNo = i+1; // setting roll no of students
		    chem_students[i].rollNo = i+1;
		    maths_students[i].rollNo = i+1;
		}
		
		for(int i=0 ; i<numOfStudents ; i++) {
			System.out.println("Student R.No. : " + phys_students[i].rollNo);
			System.out.println("Sum of marks of the student : " + (phys_students[i].marks + chem_students[i].marks + maths_students[i].marks));
			System.out.println("Average marks of the student in physics, chemistry and maths out of 100 : " + (phys_students[i].marks + chem_students[i].marks + maths_students[i].marks) / 3);
			System.out.println("--------------------------------------------------------------------------");
		}
		
		System.out.println();
		System.out.println("Sum of marks in physics of the total students : " + Physics.sum);
		System.out.println("Average marks in physics out of 100 : " + Physics.sum/numOfStudents);
		
		System.out.println();
		System.out.println("Sum of marks in chemistry of the total students : " + Chemistry.sum);
		System.out.println("Average marks in chemistry out of 100 : " + Chemistry.sum/numOfStudents);

		System.out.println();
		System.out.println("Sum of marks in maths of the total students : " + Maths.sum);
		System.out.println("Average marks in maths out of 100 : " + Maths.sum/numOfStudents);

	}
}
