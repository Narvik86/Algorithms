/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csproject1;

import java.util.NoSuchElementException;

  class GenLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;
        private T olddata;
	
	public void addFront(T d) {
		if(head == null) {
			head = new Node(d, null);
			tail = head;
		} else {
			head = new Node(d, head);
                }
		size++;
	}

	public void addEnd(T d) {
		if (head == null) {
			head = new Node(d, null);
			tail = head;
		} else {
			tail.next = new Node(d, null);
			tail = tail.next;
		}
		size++;
	}

	public T removeFront() {
		if(head == null) {
			throw new NoSuchElementException();
			
		} else if(head == tail){ // one node
                        olddata = head.data;
			head = tail = null;		
			
		} else {
			olddata = head.data;
			head = head.next;
		}
		size--;
		return olddata;
	}

	public T removeEnd() {
		
		Node current = head;
	
		if(head == null) {
			throw new NoSuchElementException();
			
		} else if(head == tail){ // one node			
			olddata = tail.data;
			head = tail = null;		
			
		} else {
			olddata = tail.data;
			
			while(current.next != tail) {
				current = current.next;
			}
			olddata = tail.data;
			current.next = null;
			tail = current; 
                        
		}
                size--;
		return olddata;
	
	}
	

	public void remove(int index) {
	
		T olddata;
		if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException();
		} else if (index == 0) 
				olddata = removeFront();
		  else if(index == size -1)
				olddata = removeEnd();		  
		  else {
			Node<T> p = head;
			Node<T> pprev = null;
			for (int k = 0; k < index; k++) {
				pprev = p;
				p = p.next;			
			}
			olddata = p.data;
			pprev.next = p.next;		
                        size--;	 
		  }                     
	}

	public void set(int index, T val){
            if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException();
		} else if (index == 0) 
				head.data = val;
		  else if(index == size -1)
				tail.data = val;
		  
		  else {
			Node<T> p = head;
			for (int k = 0; k < index; k++) {				
				p = p.next;			
			}
                        p.data = val;		
		  }
        }
        
        public T get(int index) {
            Node<T> p = head;
            if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
                    for (int k = 0; k < index; k++) {				
			p = p.next;			
                    }            
                }
            return p.data;
        }
        
        public void swap(int index1, int index2) {
            Node<T> onePrev = head;            
            Node<T> twoPrev = head;
            Node<T> one;            
            Node<T> two;
            Node<T> temp;
            Node<T> temp2;
            
            if(index1 != 0  && index2 != size - 1) {
                for(int i = 0; i < index1 - 1; i++) {
                    onePrev = onePrev.next;
                }   one = onePrev.next;
                for(int i = 0; i < index2 - 1; i++) {
                    twoPrev = twoPrev.next;
                }   two = twoPrev.next;
                
                temp = one.next;
                temp2 = two.next;
                onePrev.next = two;
                two.next = temp;
                twoPrev.next = one;
                one.next = temp2;
                
            } else if(index1 == 0 && index2 != size-1) {
                for(int i = 0; i < index2 - 1; i++) {
                    twoPrev = twoPrev.next;
                }   
                one = head;
                two = twoPrev.next;
                temp = one.next;
                temp2 = two.next;
                twoPrev.next = one;
                one.next = temp2;
                head = two;  
                head.next = temp;
                
            } else if(index1 != 0 && index2 == size-1) {
                for(int i = 0; i < index1 - 1; i++) {
                    onePrev = onePrev.next;
                }   one = onePrev.next;
                for(int i = 0; i < index2 - 1; i++) {
                    twoPrev = twoPrev.next;
                }
                
                one = onePrev.next;
                two = tail;
                onePrev.next = two;
                two.next = one.next;
                twoPrev.next = one;
                one.next = null;
                tail = one;
                
            } else if(index1 == 0 && index2 == size-1) {
                one = head;
                for(int i = 0; i < index2 - 1; i++) {
                    twoPrev = twoPrev.next;
                }   two = twoPrev.next;
                temp = one.next;
                twoPrev.next = one;
                two.next = temp;
                head = two;
                one.next = null;
                tail = one;
            }
        }
        
        public void shift(int val) {
            if(val > 0){
                for(int i = val; i > 0; i--){
                    this.addEnd(head.data);
                    this.removeFront();                    
                }
            } else if(val < 0) {
                for(int i = val; i < 0; i++){
                    this.addFront(tail.data);
                    this.removeEnd();                    
                }
            }            
        }
        
        public void removeMatching(T val) {           
            Node<T> test = head;            
            int counter = 0;
            
            for(int i = 0; i < size; i++) {
                if(test.data == val) {
                    counter++;
                }
                test = test.next;
            }
            
            test = head;            
            for(int i = 0; i < counter; i++ ) {
                int removeIndex = -1;
                for(int j = 0; j < size; j++) {
                    if(test.data == val){

                        removeIndex = j;
                    }
                    test = test.next;
                }
                if(removeIndex > -1) {;
                    remove(removeIndex);
                }
                test = head;
            }
        }
        
        public void erase(int index, int howMany) {
            Node<T> current;
            Node<T> currentPrev = head;
            
            for(int i = 0; i < index-1; i++) {
                currentPrev = currentPrev.next;
            }   current = currentPrev.next;
            
            if(howMany > (size - index)) {
                throw new ArrayIndexOutOfBoundsException();
            } else if(howMany == (size - index)) {
                currentPrev.next = null;
                tail = currentPrev;
            } else {
                for(int i = 0; i < howMany; i++) {
                    this.remove(index);
                }
            }
        }
        
        
        public void insertList(GenLinkedList<T> list, int index) {
            
            if(list.size == 0) {
                return;
            }
            
            if(head == null) {
               head = list.head;
            } else if(index == 0) {
                list.tail.next = head;
            } else {
                Node<T> currentPrev = head;
                Node<T> current;
            
                for(int i = 0; i < index-1; i++) {
                    currentPrev = currentPrev.next;
                }   current = currentPrev.next;
                
                currentPrev.next = list.head;
                list.tail.next = current;
                
            }
            
            
            
        }
        
        
///////////////////////////////////////////////////////////        

	@Override
	public String toString(){
	
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		
		Node p = head;
		
		while (p != null) {			
			sb.append(p.data).append(" ");
			p = p.next;
		}
		
		sb.append("]");
		return new String(sb);		
	}
	
	
	private static class Node<T> {
		T data;
		Node<T> next;
		
		Node(T d, Node n){
			data = d;
			next = n;	
		}
	}
	
  }

  class CSProject1 {
      public static void main (String args[]) {
		
		GenLinkedList<Object> list = new GenLinkedList<>();
		
		for(int i = 0; i< 1; i++) {
			list.addEnd(i);
		}

		System.out.println(list);
		
		for(int i = 0; i < 1; i++) {
			list.addFront(i);		
		}
		
		System.out.println(list);
		
		Object x = list.removeFront();
		Object y = list.removeEnd();
                list.addFront("A");
                list.addFront("B");
                list.addFront(3);
		System.out.println(list);
                list.set(2, "a");
                System.out.println(list);
                System.out.println(list.get(2));
//                list.swap(0, 2);
//                System.out.println(list);
                list.addFront("v");
                list.addEnd("v");
                System.out.println(list);
                list.swap(1, 3);
                System.out.println(list);
                list.addEnd("v");
                list.addEnd("v");
                System.out.println(list);
                list.addFront(2);
                System.out.println(list);
                
                System.out.println();
                list.shift(3);
                System.out.println(list);
                list.shift(-2);
		System.out.println(list);
                list.remove(2);
                System.out.println(list);
                System.out.println("--------------1");
                list.removeMatching("v");
                System.out.println(list);
                list.swap(0,2);
                System.out.println(list);
                list.addEnd(5);
                list.addFront(5);
                System.out.println(list);
                
                GenLinkedList<Object> list2 = new GenLinkedList<>();
                for(int i = 0; i < 5; i++) {
                    list2.addFront(5);
                }
                
                list.insertList(list2, 2);
                System.out.println(list);
                
	}
  }


