package game;

import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;


public class Main extends javax.swing.JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int turnNo;
    private int score;
    private int highestScore;
    
    long startTime; 
    long elapsedTime; 
    
    private Stack A;
    private Stack B;
    private Stack C;
    
    Thread thread;
    
    private Queue queue;
    
    public Main(){
        
    }
   
    public Main(int turnNo,Queue queue,int highestScore) {
        
        this.turnNo = turnNo;
        this.queue = queue;
        this.highestScore = highestScore;
        
        this.A = new Stack(); this.B = new Stack(); this.C = new Stack();
        
        initComponents();
        this.setLocationRelativeTo(null);
        
        lbl_current_turn_no.setText(String.valueOf(turnNo)); // set current turn no
        lbl_hiegst_score.setText(String.valueOf(highestScore)); // set highest Score 
        
        initializeStacks();
        
        thread = new Thread() {
            @Override
            public void run() {
                startTimer();
            }
           
        };
        thread.start();
    }
    
    private void initializeStacks(){
         Random rand = new Random();
         
         int num1 = 0,num2 = 0,num3 = 0,num4 = 0,num5 = 0,num6 = 0,num7 = 0,num8 = 0;
         
       while((num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8) != 100){
          num1   = rand.nextInt(100) + 1;
          num2 = rand.nextInt(100) + 1;
          num3 = rand.nextInt(100) + 1;
          num4 = rand.nextInt(100) + 1;
          num5   = rand.nextInt(100) + 1;
          num6 = rand.nextInt(100) + 1;
          num7 = rand.nextInt(100) + 1;
          num8 = rand.nextInt(100) + 1;
       }
         
        // set stack 1
        btn1.setText(String.valueOf(num1)); btn2.setText(String.valueOf(num2)); btn3.setText(String.valueOf(num3)); btn4.setText(String.valueOf(num4));
        
        // set stack 2
        btn5.setText(String.valueOf(num5)); btn6.setText(String.valueOf(num6)); btn7.setText(String.valueOf(num7)); btn8.setText(String.valueOf(num8));
    
        A.push(num1); A.push(num2); A.push(num3); A.push(num4);
        
        B.push(num5); B.push(num6); B.push(num7); B.push(num8);
    
    }
   
    private void startTimer(){
        startTime = System.currentTimeMillis();
        elapsedTime = 0L;

        while (elapsedTime < 120 * 1000) {
            elapsedTime = (new Date()).getTime() - startTime;
            lbl_timer.setText(String.valueOf(elapsedTime/1000));
        }
        
        int msg =  JOptionPane.showConfirmDialog(this,"Turrn is over!,Do you want to go the next round ?", "Times Up",
        JOptionPane.YES_NO_OPTION);
           
        this.queue.enqueue(0);
           if(msg == JOptionPane.YES_OPTION){
               Main m = new Main(turnNo + 1,this.queue,highestScore);
               m.setVisible(true);
               this.dispose();
           }else if(msg == JOptionPane.NO_OPTION){
               
               System.out.println(" ------------------- ");
               
               this.thread.stop();
               Results re = new Results(this.queue,this.highestScore);
               re.setVisible(true);
               this.dispose();
           }
        
    }

    // A.push(B.pop())
    private void PushAPopB(){
       int popValue = B.pop();
       
       if(popValue == 0){
           warnMsg("Stack B is empty!");
       }else{
           if(A.top == 3){
               B.push(popValue);
               warnMsg("Stack A is full!");
           }else{
               removeTopValueFromStackB(B.top + 2);
               addValueTopOfStackA(A.top + 2,popValue);
               A.push(popValue);
           }
       }
    }
    
     // A.push(C.pop())
    private void PushAPopC(){
       int popValue = C.pop();
       
       if(popValue == 0){
           warnMsg("Stack C is empty!");
       }else{
           if(A.top == 3){
               C.push(popValue);
               warnMsg("Stack A is full!");
           }else{
               removeTopValueFromStackC(C.top + 2);
               addValueTopOfStackA(A.top + 2,popValue);
               A.push(popValue);
           }
       }
    }
    
      // B.push(C.pop())
    private void PushBPopC(){
       int popValue = C.pop();
       
       if(popValue == 0){
           warnMsg("Stack C is empty!");
       }else{
           if(B.top == 3){
               C.push(popValue);
               warnMsg("Stack B is full!");
           }else{
               removeTopValueFromStackC(C.top + 2);
               addValueTopOfStackB(B.top + 2,popValue);
               B.push(popValue);
           }
       }
    }
    
      // B.push(A.pop())
    private void PushBPopA(){
       int popValue = A.pop();
       
       if(popValue == 0){
           warnMsg("Stack A is empty!");
       }else{
           if(B.top == 3){
               A.push(popValue);
               warnMsg("Stack B is full!");
           }else{
               removeTopValueFromStackA(A.top + 2);
               addValueTopOfStackB(B.top + 2,popValue);
               B.push(popValue);
           }
       }
    }
    
       // C.push(A.pop())
    private void PushCPopA(){
       int popValue = A.pop();
       
       if(popValue == 0){
           warnMsg("Stack A is empty!");
       }else{
           if(C.top == 3){
               A.push(popValue);
               warnMsg("Stack C is full!");
           }else{
               removeTopValueFromStackA(A.top + 2);
               addValueTopOfStackC(C.top + 2,popValue);
               C.push(popValue);
           }
       }
    }
    
         // C.push(B.pop())
    private void PushCPopB(){
       int popValue = B.pop();
       
       if(popValue == 0){
           warnMsg("Stack B is empty!");
       }else{
           if(C.top == 3){
               B.push(popValue);
               warnMsg("Stack C is full!");
           }else{
               removeTopValueFromStackB(B.top + 2);
               addValueTopOfStackC(C.top + 2,popValue);
               C.push(popValue);
           }
       }
    }
    
    private void removeTopValueFromStackA(int index){
        switch (index) {
            case 1:
                btn1.setText("");
                break;
            case 2:
                btn2.setText("");
                break;
            case 3:
                btn3.setText("");
                break;
            default:
                btn4.setText("");
                break;
        }
    }
    
    private void removeTopValueFromStackB(int index){
        switch (index) {
            case 1:
                btn5.setText("");
                break;
            case 2:
                btn6.setText("");
                break;
            case 3:
                btn7.setText("");
                break;
            default:
                btn8.setText("");
                break;
        }
    }
    
    private void removeTopValueFromStackC(int index){
        switch (index) {
            case 1:
                btn9.setText("");
                break;
            case 2:
                btn10.setText("");
                break;
            case 3:
                btn11.setText("");
                break;
            default:
                btn12.setText("");
                break;
        }
    }
    
    private void addValueTopOfStackA(int index,int value){
        switch (index) {
            case 1:
                btn1.setText(String.valueOf(value));
                break;
            case 2:
                btn2.setText(String.valueOf(value));
                break;
            case 3:
                btn3.setText(String.valueOf(value));
                break;
            default:
                btn4.setText(String.valueOf(value));
                break;
        }
    }
    
    private void addValueTopOfStackB(int index,int value){
        switch (index) {
            case 1:
                btn5.setText(String.valueOf(value));
                break;
            case 2:
                btn6.setText(String.valueOf(value));
                break;
            case 3:
                btn7.setText(String.valueOf(value));
                break;
            default:
                btn8.setText(String.valueOf(value));
                break;
        }
    }
    
    private void addValueTopOfStackC(int index,int value){
        switch (index) {
            case 1:
                btn9.setText(String.valueOf(value));
                break;
            case 2:
                btn10.setText(String.valueOf(value));
                break;
            case 3:
                btn11.setText(String.valueOf(value));
                break;
            default:
                btn12.setText(String.valueOf(value));
                break;
        }
    }
    
    private void checkIsBalanceStackAandB(){
        
        thread.stop(); // terminate curreunt thread
        int totalA = 0,totalB = 0;
        
        while(A.top != -1){
            totalA += A.pop();
        }
        
        while(B.top != -1){
            totalB += B.pop();
        }
        
        if(totalA == totalB){
            score = (120 - (int)(elapsedTime/1000)) * 100; // calculate score
           int msg =  JOptionPane.showConfirmDialog(this,"Your Score is " + score + "\nDo you want to go the next round ?", "Congradulations",
        JOptionPane.YES_NO_OPTION);
           
           this.queue.enqueue(score);
           if(msg == JOptionPane.YES_OPTION){
               
               if(highestScore < score) highestScore = score;
               
               Main m = new Main(turnNo + 1,queue,highestScore);
               m.setVisible(true);
               this.dispose();
           }else if(msg == JOptionPane.NO_OPTION){
               
               
               Results re = new Results(this.queue,this.highestScore);
               re.setVisible(true);
               this.dispose();
           }
           
        }else{
            int msg =  JOptionPane.showConfirmDialog(this,"You are failed!,Do you want to go the next round ?", "Try again",
        JOptionPane.YES_NO_OPTION);
           
            this.queue.enqueue(0);
           if(msg == JOptionPane.YES_OPTION){
               Main m = new Main(turnNo + 1,queue,highestScore);
               m.setVisible(true);
               this.dispose();
           }else if(msg == JOptionPane.NO_OPTION){
               
              
               Results re = new Results(this.queue,this.highestScore);
               re.setVisible(true);
               this.dispose();
           }
        }
    }
    
    // Warning Message
    private void warnMsg(String message){
        JOptionPane.showMessageDialog(this,message, "Warning",
        JOptionPane.WARNING_MESSAGE);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        stacka = new javax.swing.JPanel();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        lbl_timer = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_current_turn_no = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_hiegst_score = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        stackc = new javax.swing.JPanel();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        stackb = new javax.swing.JPanel();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 0));
        jLabel10.setText(":");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(8, 8, 194));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 247, 7));
        jLabel2.setText("BALANCE STACK A & STACK B");

        btn2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn2.setForeground(new java.awt.Color(204, 0, 0));

        btn3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn3.setForeground(new java.awt.Color(204, 0, 0));

        btn4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn4.setForeground(new java.awt.Color(204, 0, 0));

        btn1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn1.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout stackaLayout = new javax.swing.GroupLayout(stacka);
        stacka.setLayout(stackaLayout);
        stackaLayout.setHorizontalGroup(
            stackaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stackaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stackaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(btn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(btn1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addContainerGap())
        );
        stackaLayout.setVerticalGroup(
            stackaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stackaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbl_timer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_timer.setForeground(new java.awt.Color(255, 255, 0));
        lbl_timer.setText("60");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("TURN");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("S");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("TIMER");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText(":");

        lbl_current_turn_no.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_current_turn_no.setForeground(new java.awt.Color(255, 255, 0));
        lbl_current_turn_no.setText("3");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("HIGHEST SCORE");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText(":");

        lbl_hiegst_score.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_hiegst_score.setForeground(new java.awt.Color(255, 255, 0));
        lbl_hiegst_score.setText("100");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 0));
        jLabel12.setText(":");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("Stack A");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 153, 51));
        jLabel13.setText("Stack B");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 255));
        jLabel14.setText("Stack C");

        btn10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn10.setForeground(new java.awt.Color(255, 0, 255));

        btn11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn11.setForeground(new java.awt.Color(255, 0, 255));

        btn12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn12.setForeground(new java.awt.Color(255, 0, 255));

        btn9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn9.setForeground(new java.awt.Color(255, 0, 255));

        javax.swing.GroupLayout stackcLayout = new javax.swing.GroupLayout(stackc);
        stackc.setLayout(stackcLayout);
        stackcLayout.setHorizontalGroup(
            stackcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stackcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stackcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn9, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        stackcLayout.setVerticalGroup(
            stackcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stackcLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn6.setForeground(new java.awt.Color(0, 153, 51));

        btn7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn7.setForeground(new java.awt.Color(0, 153, 51));

        btn8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn8.setForeground(new java.awt.Color(0, 153, 51));

        btn5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn5.setForeground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout stackbLayout = new javax.swing.GroupLayout(stackb);
        stackb.setLayout(stackbLayout);
        stackbLayout.setHorizontalGroup(
            stackbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stackbLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stackbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn7, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(btn6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        stackbLayout.setVerticalGroup(
            stackbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stackbLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(240, 240, 240));
        jButton1.setText("A.push(B.pop())");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(240, 240, 240));
        jButton2.setText("A.push(C.pop())");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(240, 240, 240));
        jButton3.setText("B.push(A.pop())");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 153, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(240, 240, 240));
        jButton4.setText("B.push(C.pop())");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 0, 255));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(240, 240, 240));
        jButton9.setText("C.push(A.pop())");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 0, 255));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(240, 240, 240));
        jButton10.setText("C.push(B.pop())");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 102, 51));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(240, 240, 240));
        jButton12.setText("Balance");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel11)
                        .addGap(101, 101, 101)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(279, 279, 279))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(stacka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stackb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(stackc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel14))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton9)
                                        .addComponent(jButton10))
                                    .addComponent(jLabel1))))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(67, 67, 67))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_timer)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addGap(91, 91, 91)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_current_turn_no))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(168, 168, 168)
                                        .addComponent(lbl_hiegst_score))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbl_timer)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(lbl_current_turn_no)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(lbl_hiegst_score)
                    .addComponent(jLabel12))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stackb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(stackc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stacka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(30, 30, 30)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
      PushAPopB();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        PushAPopC();
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        PushBPopC();
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        PushBPopA();
    }                                        

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        PushCPopA();
    }                                        

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        PushCPopB();
    }                                         

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        checkIsBalanceStackAandB();
    }                                         

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_current_turn_no;
    private javax.swing.JLabel lbl_hiegst_score;
    private javax.swing.JLabel lbl_timer;
    private javax.swing.JPanel stacka;
    private javax.swing.JPanel stacka1;
    private javax.swing.JPanel stackb;
    private javax.swing.JPanel stackc;
    // End of variables declaration                   

    
}
