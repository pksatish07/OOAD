package org.iiitb.service;

import java.util.Random;

public class PasswordGenerator
{
	private String lCase = "abcdefghijklmnopqrstuvwxyz";
	private String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String sChar = "!@#$%^&*";
	private String intChar = "0123456789";
	private Random r = new Random();
	private String password = "";

	public String generate(int length)
	{
		System.out.println("Generating password...");
		while (password.length() != length)
		{
			int rPick = r.nextInt(4);
			if (rPick == 0)
			{
				int spot = r.nextInt(25);
				password += lCase.charAt(spot);
			} else if (rPick == 1)
			{
				int spot = r.nextInt(25);
				password += uCase.charAt(spot);
			} else if (rPick == 2)
			{
				int spot = r.nextInt(7);
				password += sChar.charAt(spot);
			} else if (rPick == 3)
			{
				int spot = r.nextInt(9);
				password += intChar.charAt(spot);
			}
		}
		System.out.println("Generated password: " + password);
		return password;
	}
}