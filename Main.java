public class Main {

	public static Filosofos[] filosofos = new Filosofos[5];
	
	public static volatile Object[] hashi = new Object[5];

  public static volatile int[] ai = new int[5];
	
	public static void main(String[] args) {
    for(int i = 0; i < 5; i++)
      ai[i]= 99;
    
    for(int i = 0; i < 5; i++)
      hashi[i]= new Object();
    
    for(int i = 0; i < 5; i++)
      filosofos[i] = new Filosofos(i);
    
    for(int i = 0; i < 4; i++)
      filosofos[i].start();
for(int i = 0; i < 10000; i++){
      System.out.print("."); 
    }
      filosofos[4].start();
	}
}

class Filosofos extends Thread {
	private int id;

  public Filosofos(int id) {
    this.id=id;
  }

	public void run() {
		while(true){
      synchronized(Main.hashi[id]) {
        if (Main.ai[id] >= 5) {
          Main.ai[id] = id;
          System.out.println(id+"- Esquerda");
          
          
        } else {
          try {
            System.out.println(id+"- Dormi--------------------------------------");
              Main.hashi[id].wait();
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
        }

        synchronized(Main.hashi[(id+1)%5]) {
          if (Main.ai[(id+1)%5] >= 5) {
            Main.ai[(id+1)%5] = id;
            if(Main.ai[id] == this.id && Main.ai[(id+1)%5] == this.id){
              System.out.println(id+"- Direita comeu");
              passarTempo();
            }
            else{
            System.out.println(id+"- perdeu a vez, fome>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
          }
          else{
            System.out.println(id+"- Fome>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
          }
            Main.ai[id] = 99;
            Main.hashi[id].notify();
            Main.ai[(id+1)%5] = 99;
            Main.hashi[(id+1)%5].notify();
        }
      }
      System.out.println(id+"- Voltarei a filosofar!");
      passarTempo();passarTempo();passarTempo();passarTempo();passarTempo();passarTempo();
    }
	}

  public void passarTempo(){
    for(int i = 0; i < 10000; i++){
      System.out.print(""); 
    }
  }
}