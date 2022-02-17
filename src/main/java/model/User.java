package model;

public interface User {
	public boolean login(String userId,String password);
	public boolean logout();
}
