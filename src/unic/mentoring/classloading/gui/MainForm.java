/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.classloading.gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import unic.mentoring.classloading.core.DirectoryClassLoader;
import unic.mentoring.classloading.core.DirectoryClassLoaderListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JTextField;

import org.apache.log4j.Logger;

import javax.swing.JCheckBox;

public class MainForm implements DirectoryClassLoaderListener
{
	private static final Logger LOG = Logger.getLogger(MainForm.class);
	
	private JFrame formMain;
	private DefaultListModel<String> listClassesModel;
	private JList<String> listClasses;
	private JButton bReload;
	private DirectoryClassLoader dcl;
	private JLabel lPath;
	private JTextField tfPath;
	private JCheckBox cbRecursive;
	private JButton bLoad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainForm window = new MainForm();
					window.formMain.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm()
	{
		listClassesModel = new DefaultListModel<>();
		recreateClassloader();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		formMain = new JFrame();
		formMain.setResizable(false);
		formMain.setTitle("HW01 - Dynamic class loading");
		formMain.setBounds(100, 100, 401, 325);
		formMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formMain.getContentPane().setLayout(null);
		
		JLabel lClasses = new JLabel("Loaded classes:");
		lClasses.setBounds(10, 37, 101, 14);
		formMain.getContentPane().add(lClasses);
		
		JScrollPane scrollClasses = new JScrollPane();
		scrollClasses.setBounds(10, 62, 375, 224);
		formMain.getContentPane().add(scrollClasses);
		
		listClasses = new JList<>();
		listClasses.setModel(listClassesModel);
		scrollClasses.setViewportView(listClasses);
		
		bReload = new JButton("Reload");
		bReload.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String directory = tfPath.getText().trim();
				
				if (directory.length() > 0)
					reloadClasses(directory, cbRecursive.isSelected());
			}
		});
		bReload.setBounds(212, 33, 89, 23);
		formMain.getContentPane().add(bReload);
		
		lPath = new JLabel("Watch for:");
		lPath.setBounds(10, 11, 101, 14);
		formMain.getContentPane().add(lPath);
		
		cbRecursive = new JCheckBox("Recursive");
		cbRecursive.setSelected(true);
		cbRecursive.setBounds(117, 35, 89, 19);
		formMain.getContentPane().add(cbRecursive);
		
		tfPath = new JTextField("ext");
		tfPath.setBounds(118, 8, 267, 19);
		formMain.getContentPane().add(tfPath);
		tfPath.setColumns(10);
		
		bLoad = new JButton("Load");
		bLoad.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String directory = tfPath.getText().trim();
				
				if (directory.length() > 0)
					addClasses(directory, cbRecursive.isSelected());
			}
		});
		bLoad.setBounds(311, 33, 74, 23);
		formMain.getContentPane().add(bLoad);
	}
	
	protected void recreateClassloader()
	{
		listClassesModel.removeAllElements();
		
		if (dcl != null)
			dcl.die();
		
		try
		{
			dcl = new DirectoryClassLoader();
			dcl.addListener(this);
		}
		catch (IOException e)
		{
			LOG.error("Can't create classLoader: " + e.getMessage());
		}
	}
	
	protected void reloadClasses(String directory, boolean recursive)
	{
		recreateClassloader();
		addClasses(directory, recursive);
	}
	
	protected void addClasses(String directory, boolean recursive)
	{
		try
		{
			dcl.registerDirectory(directory, recursive);
		}
		catch (IOException e)
		{
			LOG.error("Can't load: " + e.getMessage());
		}
	}

	@Override
	public void classLoaded(Class<?> claŝŝ)
	{
		listClassesModel.addElement( claŝŝ.toString() );
	}
}