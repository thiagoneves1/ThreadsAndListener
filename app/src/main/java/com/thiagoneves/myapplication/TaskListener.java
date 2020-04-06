package com.thiagoneves.myapplication;/*
Write a class that can receive a list of tasks to do, execute them in series or in parallel, and notify the caller when:
- Each task is finished.
- All of them are finished.
- What the results were for those tasks (success, failure, error messages).
*/

import java.util.List;

interface TaskListener {
    void finish(int idTask);
    void failure(int IdTask, String messageError);
 
}

interface TodoTask {
   void process(TaskListener listener);
}

class Main implements Execution {

    private List<TodoTask> listTodo = getFromDataBase();

    Process process = new Process(this, listTodo);
    new Thread(process).start();
   
            
    void processOk(int id) {
        System.out.println("Finished "  + id);
    }
    
    void processFailure(int id, Exception e) {
        System.out.println("Exception "  + e.getMessage() );
    }
    
    
    void quantityToFinish(int quantity) {
        if (quantity == 0) {
        System.out.println("finished ");
        }
        System.out.println("quantityToFinish "  + quantity + "of " + listTodo.size());
    }
}

private Process implements Runnable, TaskListener {
    enum Mode {
       SERIES,
       PARALLEL
    }
    
    Execution callback;
    private List<TodoTask> listTodo;
    private int processed = 0;
    private int indice = 0;
    private int quantityToFinish;
    private Mode mode;
    
    
    public Process(Execution callback, List<TodoTask> listTodo, Mode mode) {
        this.callback = callback;
        this.listTodo = listTodo;
        this.quantityToFinish = listTodo.size();
        this.mode = mode;
    }


    private void run() {
    
        if (mode == PARALLEL) {
            for (TodoTask todo : listTodo) {
                try {
                      todo.process(this);
                } catch(Exception e) {
                    System.out.println("Exception "  + e.getMessage() );
                
                }
            }         
        } else {
            // SERIES
            if (quantityToFinish > 0) {
                processNext();
               
            }
           
        }
    } 
    
    void finish(int idTask) {
        anotherTaskFinished();
        callback.processOk(idTask);
        
    }
    
    void failure(int IdTask, String messageError) {
            anotherTaskFinished();
        callback.processFailure(idTask, messageError);
    }
    
       void anotherTaskFinished() {
           processed = processed +1;
           quantityToFinish = listTodo.size() - processed;
           
           callback.quantityToFinish(quantityToFinish);
           
           if (quantityToFinish > 0 && mode == SERIES) {
               processNext();
           }
          
       }
       
       void processNext() {
            listTodo.get(indice).process(this);
            indice = indice + 1;
       }

}

interface Execution {
    void processOk(int id);
    void processFailure(int id, String e);
    void quantityToFinish(int quantity)
}
