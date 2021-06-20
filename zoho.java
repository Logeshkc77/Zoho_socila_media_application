import java.util.*;
class profile_store{
	List<String> profile=new ArrayList<String>();
	List<profile_store> friends=new ArrayList<profile_store>();
	List<profile_store> friends_request=new ArrayList<profile_store>();
	// List<profile_store> friendsProfile=new ArrayList<profile_store>();

	public profile_store(List<String> profile){
		this.profile=profile;
	}
	public void display(){
		System.out.println("Name          :"+this.profile.get(0));
		System.out.println("Password      :"+this.profile.get(0));
		System.out.println("Email Id      :"+this.profile.get(0));
		System.out.println("Phone Number  :"+this.profile.get(0));
	}
}
class zoho{
	public static void main(String[] args){
		Scanner getInput = new Scanner(System.in);
		List<profile_store> allProfile=new ArrayList<profile_store>();
		ArrayList<Integer> tempId = new ArrayList<Integer>();
		int ch,temp;
		do{
			System.out.println("1. Sign Up\n2. Login\n0. quit\n");
			ch=getInput.nextInt();
			if(ch==1){
				String name,password,email,phone;
				System.out.println("Enter the name:");
				name=getInput.next();
				System.out.println("Enter the Password:");
				password=getInput.next();
				System.out.println("Enter the Email:");
				email=getInput.next();
				System.out.println("Enter the Phone Number:");
				phone=getInput.next();
				//System.out.println(name+" "+password+" "+email+" "+phone);
				int repected=1;
				for (int i = 0; i < allProfile.size(); i++) {
		            String profileName = allProfile.get(i).profile.get(0);
		            if(profileName.equalsIgnoreCase(name)==true){
		            	repected=0;
		            	break;
		            }
		        }
		        if(repected==1){
			        List<String> profile=new ArrayList<String>();
			        profile.add(name);
			        profile.add(password);
			        profile.add(email);
			        profile.add(phone);
			        allProfile.add(new profile_store(profile));
			        System.out.println(name+" "+password+" "+email+" "+phone);
			        System.out.println("Added Successfully!!!");
			    }else{
			    	System.out.println("User Name is alredy Used. Please try another username");
			    }
			}
			else if(ch==2){
				String name,password;
				System.out.println("Enter the name:");
				name=getInput.next();
				System.out.println("Enter the Password:");
				password=getInput.next();
				for (int i = 0; i < allProfile.size(); i++) {
		            String profileName = allProfile.get(i).profile.get(0);
		            String profilePassword = allProfile.get(i).profile.get(0);
		            if(profileName.equalsIgnoreCase(name)==true && profilePassword.equals(password)==true){
		            	System.out.println("Login Done");
		            	profile_store currentUserProfile=allProfile.get(i);
		            	int signout=0;
		            	while(signout!=1){
		            		System.out.println("1. Profile\n2. Search Buddies\n3. Friend Request \n4. Approve Request \n5. Signout");
		            		// int ch;
		            		ch=getInput.nextInt();
		            		switch(ch){
		            			case 1:
		            				currentUserProfile.display();
		            				break;
		            			case 2:
		            				if(currentUserProfile.friends.size()==0){
		            					System.out.println("You have no friends...!!!");
		            					break;
		            				}
		            				System.out.println("Your Friends List:");
		            				System.out.println("Temp Id\tName");
		            				for (int j = 0; j < currentUserProfile.friends.size(); j++) {
							            String friendsName = currentUserProfile.friends.get(j).profile.get(0);
							            System.out.println((j+1)+"   \t"+friendsName);
							            tempId.add(j+1);
							        }

							        System.out.println("Enter Search Temp Id:");
							        temp=getInput.nextInt();
							        while((tempId.contains(temp))==false){
							        	System.out.println("Temp Id Not Found Try again..");
							        	System.out.println("Enter Search Temp Id:");
							        	temp=getInput.nextInt();
							        }
							        currentUserProfile.friends.get(temp-1).display();
							        tempId.clear();
							        break;
							    case 3:
							    	if(allProfile.size()==1){
		            					System.out.println("No user...!!!");
		            					break;
		            				}
							    	System.out.println("All Profile List:");
		            				System.out.println("Temp Id\tName");
							    	for (int j = 0; j < allProfile.size(); j++) {
							            String allProfileName = allProfile.get(j).profile.get(0);
							            if(allProfileName.equalsIgnoreCase(name)!=true){
							            	System.out.println((j+1)+"   \t"+allProfileName);
							            	tempId.add(j+1);
							            }
							        }
							        System.out.println("Enter Friend Temp Id:");
							        temp=getInput.nextInt();
							        while((tempId.contains(temp))==false){
							        	System.out.println("Temp Id Not Found Try again..");
							        	System.out.println("Enter Search Temp Id:");
							        	temp=getInput.nextInt();
							        }
							        allProfile.get(temp-1).friends_request.add(currentUserProfile);
							        tempId.clear();
							    	break;
							    case 4:
							    	if(currentUserProfile.friends_request.size()==0){
		            					System.out.println("You have no friends request...!!!");
		            					break;
		            				}
							    	System.out.println("All Friend request List:");
		            				System.out.println("Temp Id\tName");
		            				for(int j=0;j<currentUserProfile.friends_request.size();j++){
		            					System.out.println((j+1)+"   \t"+currentUserProfile.friends_request.get(j).profile.get(0));
		            					tempId.add(j+1);
		            				}
		            				System.out.println("Enter Friend Temp Id:");
							        temp=getInput.nextInt();
							        while((tempId.contains(temp))==false){
							        	System.out.println("Temp Id Not Found Try again..");
							        	System.out.println("Enter Search Temp Id:");
							        	temp=getInput.nextInt();
							        }
							        currentUserProfile.friends.add(currentUserProfile.friends_request.get(temp-1));
							        currentUserProfile.friends_request.get(temp-1).friends.add(currentUserProfile);
							        currentUserProfile.friends_request.remove(temp-1);
							        tempId.clear();
							    	break;
							    case 5:
							    	signout=1;
							    	break;
		            		}
		            	}
		            	break;
		            }
		        }
			}
		}while(ch!=0);
	}
}