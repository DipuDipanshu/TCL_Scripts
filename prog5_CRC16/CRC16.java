import java.util.Scanner;

class CRC16{

	void computeCRC(int gen[],int rem[]) {
		int current = 0;
		while(true) {
			for(int i = 0;i < gen.length;i++)
				rem[current+i] = rem[current+i] ^ gen[i];
			while(rem[current] == 0 && current != rem.length-1)
				current++;
			if((rem.length - current) < gen.length)
				break;
		}
	}

	public static void main(String []args) {

		int app_msg[],rem[];
		String value;
		Scanner sc = new Scanner(System.in);
	
		System.out.print("Enter the message bits :\t");
		value = sc.next();
		int msg[] = new int[value.length()];
		for(int i = 0;i < value.length();i++)
			msg[i] = Character.getNumericValue(value.charAt(i));
		
		int gen[] = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};

		app_msg = new int[value.length() + 16];
		for(int i = 0;i < value.length() + 16;i++){
			if(i < value.length())
				app_msg[i] = msg[i];
			else
				app_msg[i] = 0;
		}

		rem = app_msg;
		CRC16 c = new CRC16();
		c.computeCRC(gen,rem);

		//Skipping extra zeroes at the beginning of rem 
		int j = 0;
		while(j<msg.length)
			j++;

		System.out.print("CRC-16 Generator    :\t");
		for(int i = j;i < gen.length;i++)
			System.out.print(gen[i]);
		System.out.print("\nRemainder obtained  :\t");
		for(int i = j;i < rem.length;i++)
			System.out.print(rem[i]);
		System.out.print("\nTransmitted message :\t");
		for(int i : msg)
			System.out.print(i);
		for(int i = j;i < rem.length;i++)
			System.out.print(rem[i]);

		System.out.print("\n\nEnter message bits received :\t");
		String recv = sc.next();
		int temp_rem[] = new int[recv.length()];
		for(int i = 0;i < recv.length();i++)
			temp_rem[i] = Character.getNumericValue(recv.charAt(i));

		c.computeCRC(gen,temp_rem);

		boolean corrupted = false;
		for(int i : temp_rem){
			if(i == 1){
				corrupted = true;
				break;
			}
		}

		if(corrupted)
			System.out.println("\nReceived message IS corrupted.");
		else
			System.out.println("\nReceived message is NOT corrupted.");			
	}
}

