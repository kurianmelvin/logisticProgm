package logApp.Facility.Facility;

import java.util.HashMap;

public class ScheduleImpl implements Schedule{
	
	private HashMap<Integer,Integer> schedule;
	private int itemsPerDay;
	
		public ScheduleImpl(int itemsPerDay)
		{
			schedule = new HashMap<Integer,Integer>();
			this.itemsPerDay=itemsPerDay;
		}
		
		public int processRatePerDayCounter(int startingDay, int quantity)
		{
			int processed = 0;
			int current = startingDay;
			int count=0;
			while(processed<quantity)
			{
				count++;
				processed+=itemsPerDay-checkDay(current);
				current++;
			}
			return count;
		}
		
		public boolean orderTime(int startingDay,int quantity)
		{
			int processed = 0;
			int current = startingDay;
			while(processed<quantity)
			{
				int availible = itemsPerDay-checkDay(current);
				if(processed+availible<=quantity)
					schedule.put(current, itemsPerDay);
				else
					schedule.put(current,checkDay(current)+(quantity-processed));
				current++;
				processed+=availible;
			}
			return true;
		}
		
		public void print()
		{
			System.out.println("------------------------");
			for(int i=0;i<=20;i++)
			{
			String format = "   %1$-1s%2$-1s\n";
			
				System.out.format(" \t\t Day: %1$-1s \nAvililable: %2$-10s ",  i,(itemsPerDay-checkDay(i)));
			
				
			}
			
			
		}
		
		private int checkDay(int day)
		{
			if(!schedule.containsKey(day))
				return 0;
			else return schedule.get(day);
		}
	
}
