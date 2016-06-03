package model;

import java.util.Date;

public abstract class AbstractResource implements IResource{
	protected long id;
	protected Date start;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
}