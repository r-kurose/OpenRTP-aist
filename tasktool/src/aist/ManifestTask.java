package aist;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class ManifestTask extends Task {

	String file;
	String key;
	String prop;

	@Override
	public void execute() throws BuildException {
		try {
			FileInputStream is = new FileInputStream(file);
			Manifest mf = new Manifest(is);
			Attributes attr = mf.getMainAttributes();
			String value = attr.getValue(key);
			if (value == null) {
				value = "";
			}
			getProject().setUserProperty(prop, value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setProperty(String prop) {
		this.prop = prop;
	}

}
