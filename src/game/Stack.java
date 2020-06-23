package game;

//Stack Implementation Class
public class Stack {
 
 static final int MAX = 4; 
 int top; 
 int a[] = new int[MAX]; // Maximum size of Stack 
 
 Stack()  // static block
 { 
     top = -1; 
 } 

 boolean isEmpty() 
 { 
     return (top < 0); 
 } 
 
 boolean push(int x) 
 { 
     if (top >= (MAX - 1)) { 
         return false;
     } 
     else { 
         a[++top] = x; 
         return true;
     } 
 } 

 int pop() 
 { 
     if (top < 0) { 
         return 0; 
     } 
     else { 
         int x = a[top--]; 
         return x; 
     } 
 } 

 int peek() 
 { 
     if (top < 0) { 
         return 0; 
     } 
     else { 
         int x = a[top]; 
         return x; 
     } 
 } 
}
