package game;

class Queue { // Queue Implementation
    
    Node front, rear; 
  
    public Queue() 
    { 
        this.front = this.rear = null; 
    } 
  
    void enqueue(int key) 
    { 
        Node temp = new Node(key);   
        if (this.rear == null) { 
            this.front = this.rear = temp; 
            return; 
        } 
        this.rear.next = temp; 
        this.rear = temp; 
    } 
  
    void dequeue() 
    { 
        if (this.front == null) 
            return;
        Node temp = this.front; 
        this.front = this.front.next; 
        if (this.front == null) 
            this.rear = null; 
    } 
} 