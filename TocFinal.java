import java.util.*;
import java.net.URL;
import java.io.*;

public class TocFinal {

	public static int testnum = 0;
	public static void main(String[] args) throws IOException, NumberFormatException{
		
		HashMap My_table = new HashMap<String , Integer >();
		HashMap Last_table = new HashMap<Integer , ArrayList < String > >();
		java.io.InputStream in = System.in;
		int stop;
		//URL open
		if(args.length < 1)
		{
			System.out.println("No input Or input Error");
			return ;
		}
		String net = args[0];
		int maxnum = Integer.parseInt(args[1]);
		int Lnum = Integer.parseInt(args[2]);
		URL url = new URL(net);
		BufferedReader input = new BufferedReader(  new InputStreamReader(url.openStream(),"UTF-8"));
		
		String inputLine ;
		while ((inputLine = input.readLine()) != null)
	    {
			ArrayList <String > temp =  new ArrayList<String >();
			ArrayList < String > Aftercom = new ArrayList< String > ();
			temp = findattribute(inputLine);
			//	System.out.println(temp.size());
			/*for(int i = 0 ; i < temp.size() ; i ++)
			{
				System.out.println( temp.get(i));
			}*/
			
			Aftercom = combstr(temp , Lnum);
			for(int i = 0 ; i < Aftercom.size() ; i++)
			{
				if(!My_table.containsKey(Aftercom.get(i)))
				{
					My_table.put(Aftercom.get(i), 1);
				}
				else 
				{
					int getvalue  =(int) My_table.get(Aftercom.get(i));
					My_table.put(Aftercom.get(i), getvalue+1);
				}
			}/*
			System.out.println("~~~~~~~~~~~~~~~~~~~count~~~~~~~~~~~~~~~~~");
			
			for (Object key :  My_table.keySet()) {
	            System.out.println(key + " : " + My_table.get(key));
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~count end~~~~~~~~~~~~~~~~~");
			stop = in.read();*/
			
			
			
	    }
		int max = 0 ;
		//int min;
		for (Object key :  My_table.keySet()) {
            int tempint =(int) My_table.get(key);
            if(tempint > max ) max = tempint;
            ArrayList <String > templist = new ArrayList < String >();
            if(!Last_table.containsKey(tempint))
            {
            	templist.add((String)key);
            	Last_table.put(tempint, templist);
            }
            else
            {
            	templist=(ArrayList<String>) Last_table.get(tempint);
            	templist.add((String)key);
            	Last_table.put(tempint, templist);
            	
            }
            
		}
		//System.out.println("zxcvbjkdfgfdskdsbkjdhmfbv,");
		int count = 1 ;
		BufferedWriter bw = new BufferedWriter (new FileWriter("output.txt"));
		for(int i = max ; i >= 0 ; i--)
		{
			ArrayList <String > templist = new ArrayList < String >();
			if(Last_table.containsKey(i))
			{
				templist = (ArrayList<String>) Last_table.get(i);
				for(int j = 0 ; j < templist.size() ;  j ++)
				{
					//System.out.println(count++ + templist.get(j) + " : " + i );
					
					String tttstr = templist.get(j) + ";" + i + "\n";
					bw.write( tttstr, 0, tttstr.length( ) );
				}
				maxnum -= templist.size();
			}
			
			if(maxnum <= 0 )
				break;
		}
		bw.close();
		//URL close
		//System.out.println("The end");
		in.close();
		
		// TODO Auto-generated method stub

	}
	static ArrayList<String> findattribute(String line)
	{
		ArrayList<String >	Answer = new ArrayList<String >();
		String all[] = line.split(":|,|}|\\{");
		
		for(int i = 0 ; i < all.length ; i++)
		{
			if(!all[i].equals("{") && all[i].length() != 0 && !all[i].equals("[") && !all[i].equals("]") )
			{
				
				
				if(all[i].charAt(0)== '"'  )
				{									
						all[i] = all[i].substring(1,all[i].length()-1);		
				}
				if(i%2==0)
				{
					//System.out.println("{"+i/2+"}"+all[i] );
					Answer.add( all[i-1]+":"+all[i]);
					//Answer.add(all[i]);
				}
			}
			

		}
		return Answer;

	}
	static ArrayList<String > combstr(ArrayList< String > input,int a )
	{
		ArrayList <String > Answer = new ArrayList< String > ();
		
		if(a == 2)
		{
			for( int i = 0 ; i < input.size() ; i ++)
			{
				
				String tempi = input.get(i);
				if(tempi.charAt(tempi.length()-1) == ':'){continue;}
				for(int j = i + 1 ; j < input.size() ; j ++)
				{
					String tempj = input.get(j);
					if(tempj.charAt(tempj.length()-1) == ':'){continue;}
					String temp = input.get(i)+"," + input.get(j);
					Answer.add(temp);
				}
			}
		}
		else if (a == 3 )
		{
			for( int i = 0 ; i < input.size() ; i ++)
			{
				String tempi = input.get(i);
				if(tempi.charAt(tempi.length()-1) == ':'){continue;}				
				for(int j = i + 1 ; j < input.size() ; j ++)
				{
					String tempj = input.get(j);
					if(tempj.charAt(tempj.length()-1) == ':'){continue;}
					for( int k = j + 1 ; k < input.size() ; k ++)
					{
						String tempk = input.get(k);
						if(tempk.charAt(tempk.length()-1) == ':'){continue;}
						String temp = input.get(i)+"," + input.get(j)+","+ input.get(k);
						
						Answer.add(temp);
					}
				}
			}
		}
		else if (a == 4)
		{
			for( int i = 0 ; i < input.size() ; i ++)
			{
				String tempi = input.get(i);
				if(tempi.charAt(tempi.length()-1) == ':'){continue;}
				for(int j = i + 1 ; j < input.size() ; j ++)
				{
					String tempj = input.get(j);
					if(tempj.charAt(tempj.length()-1) == ':'){continue;}
					for( int k = j + 1 ; k < input.size() ; k ++)
					{
						String tempk = input.get(k);
						if(tempk.charAt(tempk.length()-1) == ':'){continue;}
						for(int l = k + 1 ; l < input.size() ; l ++)
						{
							String templ = input.get(l);
							if(templ.charAt(templ.length()-1) == ':'){continue;}
							String temp = input.get(i) +","+ input.get(j)+","+ input.get(k) +","+ input.get(l);
							Answer.add(temp);
						}
					}
				}
			}
		}
		else 
		{
			return null;
		}
		
		return Answer;
	}

}

