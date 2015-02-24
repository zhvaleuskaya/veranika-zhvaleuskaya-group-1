/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 7:08:08 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging;

import java.util.HashMap;
import java.util.Map;

public class ControlBus
{
	private static String DATA_COUNT = "count";
	private static String DATA_AVERAGE_PERIOD = "averagePeriod";
	
	private Map<String, Object> data;
	private long timeStart;
	
	public ControlBus()
	{
		data = new HashMap<String, Object>();
		timeStart = System.currentTimeMillis();
	}
	
	public void processMessage(Message message)
	{
		updateCount();
	}
	
	private void updateCount()
	{
		Long count = (Long) data.get(DATA_COUNT);
		count = count == null ? 0 : count + 1;
		data.put(DATA_COUNT, count);
	}
	
	private void updateAveragePeriod()
	{
		Long count = (Long) data.get(DATA_COUNT);
		
		if (count != null)
		{
			Long averagePeriod = (System.currentTimeMillis() - timeStart) / count;
			data.put(DATA_AVERAGE_PERIOD, averagePeriod);
		}
	}
	
	public Map<String, Object> getData()
	{
		updateAveragePeriod();
		return data;
	}
}