/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:04:31 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.core;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Converter<SOURCE, TARGET>
{
	protected abstract void populate(SOURCE source, TARGET target);
	
	public abstract TARGET createTarget();
	
	public TARGET convert(SOURCE source)
	{
		if (source == null)
		{
			return null;
		}
		
		TARGET target = createTarget();
		populate(source, target);
		
		return target;
	}
	
	public Collection<TARGET> convertAll(Collection<SOURCE> sources)
	{
		if (sources == null)
		{
			return null;
		}
		
		Collection<TARGET> targets = new ArrayList<>( sources.size() );
		
		for (SOURCE source : sources)
		{
			targets.add( convert(source) );
		}
		
		return targets;
	}
}