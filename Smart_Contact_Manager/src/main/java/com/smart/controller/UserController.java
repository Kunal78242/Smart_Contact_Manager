package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
 
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private ContactRepository contactRepository;
  
  @ModelAttribute
  public void addCommonData(Model model,Principal principal){
   String userName=principal.getName();
   System.out.println("USERNAME" +userName);
   
   //get the using username(Email)
   User user=userRepository.getUserByuserName(userName);
   
   System.out.println("USER" +user);
   model.addAttribute("user",user);
  }
 
 //dashboard home
 @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
 public String dashboard(Model model, Principal principal) {
     //String userName = principal.getName();
     //System.out.println("Email: " + userName);
    //model.addAttribute("title", "User Dashboard");
     return "normal/user_dashboard";
 }
 // open add form handle
 @GetMapping("/add-contact")
 public String openContactForm(Model model)
 {
  model.addAttribute("title","Add Contact");
  model.addAttribute("contact",new Contact());
  return "normal/add_contact_form";
  
 }

 
 //Processing add contact form
 @PostMapping("/process-contact")
 public String processContact(
     @ModelAttribute Contact contact,
     @RequestParam("profileImage") MultipartFile file,
     Principal principal)
 
 
 {
     try {
    	 
     
    	 String name=principal.getName();
    	 User user=this.userRepository.getUserByuserName(name);
    	
    	 
    	 
    	 
    	 //processing and uploading file
    	 if(file.isEmpty())
    	 {
    		 //if the file is empty then try our message
    		 System.out.println("File is Empty");
    		 contact.setImage("contact.png");
    	 }
    		 
    		 else {
    			 //file to folder and update the name to contact
    			 contact.setImage(file.getOriginalFilename());
    			 
    			 File saveFile=new ClassPathResource("static/img").getFile();
    			 
    			 Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
    			 
    			 Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
    			 
    			 System.out.println("Image is uploaded");
    		 }
    	 
    	
    	 contact.setUser(user);
    	 user.getContacts().add(contact);
    	 
    	 this.userRepository.save(user);
    	 
    	 System.out.println("DATA"+contact);
    	 System.out.println("Added to data base");
     
     }
     catch(Exception e) {
    	 System.out.println("ERROR"+e.getMessage());
    	 e.printStackTrace();
     }

 
 return "normal/add_contact_form";
}
 
 //show conatacts
 @GetMapping("/show-contacts/{page}")
 public String showContacts(@PathVariable("page") Integer page, Model model, Principal principal) {
     
    
     model.addAttribute("title", "Show User Contacts");
     
     
     String userName = principal.getName();
     
     
     User user = this.userRepository.getUserByuserName(userName);
     
     Pageable pageable=PageRequest.of(page, 10);
    Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(),pageable);
     
     // Add contacts to model
     model.addAttribute("contacts", contacts);
     model.addAttribute("currentPage", page);
     model.addAttribute("totalPages", contacts.getTotalPages());
     return "normal/show_contacts";
 }
 
 //showing particular contact details
 @RequestMapping("/{cId}/contact")
public String showContactDetail(@PathVariable("cId")Integer cId, Model model,Principal principal)
{
	System.out.println("CID"+cId);
	
	Optional<Contact>contactOptional=this.contactRepository.findById(cId);
	Contact contact=contactOptional.get();
	
	String userName=principal.getName();
	  User user=this.userRepository.getUserByuserName(userName);
	  
	  if(user.getId()==contact.getUser().getId())
	  {  
	  model.addAttribute("contact",contact);
	  model.addAttribute("title",contact.getName());
	  }
	  return "normal/contact_detail";
	 
}
 
 //delete contact handler
 @GetMapping("/delete/{cId}")
 public String deleteContact(@PathVariable("cId")Integer cId, Model model, HttpSession session)
 {
	 // Optional<Contact>contactOptional=this.contactRepository.findById(cId);
	  
	 
	  System.out.println("CID"+cId);
	  //Contact contact=contactOptional.get();
	  
	  Contact contact=this.contactRepository.findById(cId).get();
	  
	  System.out.println("Contact"+contact.getcId());
	  contact.setUser(null);
	  this.contactRepository.delete(contact);
	  System.out.println("DELETED");
	  session.setAttribute("message",new Message("contact deleted succesfully...","success"));
	  
	  return "redirect:/user/show-contacts/0";
	}
 
//Open Update form handle
//Open Update form handler - FIXED: Changed from PostMapping to GetMapping
@GetMapping("/update/{cid}")
public String updateForm(@PathVariable("cid") Integer cid, Model m, Principal principal) {
  m.addAttribute("title", "Update Contact");
  
  Contact contact = this.contactRepository.findById(cid).get();
  
  // Add security check to ensure user owns this contact
  String userName = principal.getName();
  User user = this.userRepository.getUserByuserName(userName);
  
  if (user.getId() == contact.getUser().getId()) {
      m.addAttribute("contact", contact);
      return "normal/update_form";
  } else {
      // Redirect if user doesn't own this contact
      return "redirect:/user/show-contacts/0";
  }
}

 
//your profile handle
@GetMapping("/profile")
public String yourProfile(Model model)
{
	model.addAttribute("title","Profile Page");
	return "normal/profile";
}

//Process update contact
@PostMapping("/process-update")
public String processUpdate(
     @ModelAttribute Contact contact,
     @RequestParam("profileImage") MultipartFile file,
     Principal principal,
     HttpSession session) {
 
 try {
     String userName = principal.getName();
     User user = this.userRepository.getUserByuserName(userName);
     
     // Get the existing contact from database
     Contact oldContact = this.contactRepository.findById(contact.getcId()).get();
     
     // Check if user owns this contact
     if (user.getId() != oldContact.getUser().getId()) {
         session.setAttribute("message", new Message("You don't have permission to update this contact!", "danger"));
         return "redirect:/user/show-contacts/0";
     }
     
     // Process image if new file is uploaded
     if (!file.isEmpty()) {
         // Delete old image if not default
         if (!oldContact.getImage().equals("contact.png")) {
             File deleteFile = new ClassPathResource("static/img").getFile();
             File oldImageFile = new File(deleteFile, oldContact.getImage());
             oldImageFile.delete();
         }
         
         // Upload new image
         contact.setImage(file.getOriginalFilename());
         File saveFile = new ClassPathResource("static/img").getFile();
         Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
         Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
     } else {
         // Keep old image if no new image uploaded
         contact.setImage(oldContact.getImage());
     }
     
     // Set user and save
     contact.setUser(user);
     this.contactRepository.save(contact);
     
     session.setAttribute("message", new Message("Contact updated successfully!", "success"));
     
 } catch (Exception e) {
     System.out.println("ERROR: " + e.getMessage());
     e.printStackTrace();
     session.setAttribute("message", new Message("Something went wrong: " + e.getMessage(), "danger"));
 }
 
 return "redirect:/user/show-contacts/0";
}
}


       