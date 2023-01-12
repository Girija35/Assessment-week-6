class Teacher extends Thread {

    Object test;

    Teacher(Object test) {
        this.test = test;
    }

    public void run() {
        synchronized (test) {
            test.notify();
            System.out.println("you can start the test");

        }
    }
}
class Student extends Thread {
    boolean flag = false;
    Object test;

    Student(Object test) {
        this.test = test;
    }

    public void run() {
        synchronized (test) {
            if (!flag) {
                try {
					System.out.println("waiting for permission to start test");
                    test.wait();
                } catch (InterruptedException e) {
                }
                
            }
			
            System.out.println("Student started the test");
        }
    }
}
class Inter{
    public static void main(String[] args) {
        Object test = new Object();
        Teacher t = new Teacher(test);
        Student s = new Student(test);
        s.start();
        t.start();
    }
}