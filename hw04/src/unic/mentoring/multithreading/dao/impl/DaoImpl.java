/**
 * @author Unic
 * "hw01_classLoading" project, Nov 3, 2014, 12:03:29 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import unic.mentoring.multithreading.dao.Dao;
import unic.mentoring.multithreading.exception.DaoException;
import unic.mentoring.multithreading.service.impl.ServiceImpl;

public class DaoImpl implements Dao
{
	private static final Logger LOG = Logger.getLogger(ServiceImpl.class);
	
	public Object readItem(File file) throws DaoException
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream( new FileInputStream(file) );
			Object o = ois.readObject();
			ois.close();
			
			LOG.debug("Object has been readed: " + o.toString());
			
			return o;
		}
		catch (ClassNotFoundException | IOException e)
		{
			throw new DaoException("Can't read object: " + e.getMessage());
		}
	}
	
	public void writeItem(File file, Object o) throws DaoException
	{
		try
		{
			String path = file.getAbsolutePath();
			
			if (!file.exists())
			{
				Files.createFile( Paths.get(path) );
			}
			
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( new File(path) ) );
			oos.writeObject(o);
			oos.close();
			
			LOG.debug("Object has been written: " + o.toString());
		}
		catch (IOException e)
		{
			throw new DaoException("Can't read object: " + e.getMessage());
		}
	}
}