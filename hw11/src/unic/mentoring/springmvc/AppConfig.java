/**
 * @author Unic
 * "hw11" project, Dec 30, 2014, 11:19:21 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter
{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
//		registry.addResourceHandler("/res/**").addResourceLocations("(/res/");
		
		registry
				.addResourceHandler("/res/**")
				.addResourceLocations("/res/")
				.setCachePeriod(3600).resourceChain(true)
				.addResolver(new PathResourceResolver());
	}
}