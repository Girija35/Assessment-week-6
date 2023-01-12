class Clg{
	Object clg,dept;
	Clg(Object clg,Object dept)
	{
		this.clg=clg;
		this.dept=dept;
	}
}

class Resource1 extends Thread{
	Clg c;
	Resource1(Clg c)
	{
		this.c=c;
	}
	public void run()
	{
        synchronized(c.clg){
            System.out.println("New Clg");
            try{
                Thread.sleep(1000);
            }catch(Exception e){
            }
        }
        synchronized(c.dept){
             System.out.println("New Dept");
        }
    }
}
class Resource2 extends Thread{
	Clg c;
	Resource2(Clg c)
	{
		this.c=c;
	}
	public void run()
	{
        synchronized(c.dept){
            System.out.println("Dept");
            try{
                Thread.sleep(1000);
            }catch(Exception e){
            }
        }
        synchronized(c.clg){
            System.out.println("Clg");
        }
    }
}
class Deadlock{
	public static void main(String[] args)
	{
		Object clg=new Object(); 
		Object dept=new Object();
		Clg c=new Clg(clg,dept);
		Resource1 r1=new Resource1(c);
		Resource2 r2=new Resource2(c);
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		t1.start();
		t2.start();
	}
}