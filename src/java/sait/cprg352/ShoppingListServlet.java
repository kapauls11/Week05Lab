package sait.cprg352;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet 
{    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {  
        HttpSession session = request.getSession();
        
        String username = (String) session.getAttribute("username");
        
        ArrayList<String> itemList;
        
        String action = request.getParameter("action");
        
        if(action==null)
        {
            if(username==null) {
                
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
            else
            {
                itemList = (ArrayList<String>)session.getAttribute("itemlist");
                
                if(itemList!=null)
                {
                    int listSize = itemList.size();
                    request.setAttribute("listsize", listSize);
                    request.setAttribute("itemlist", itemList);
                }
                else
                {
                    request.setAttribute("listsize", 0);           
                }
                request.setAttribute("username", username);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
        }
        else if(action.equals("logout"))
        {
            session.invalidate();
            request.setAttribute("registerMessage", "Logged Out");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       HttpSession session = request.getSession();
       
       String action = request.getParameter("action");
       String username = request.getParameter("username");
       
       ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("itemlist");
       
       if(itemList==null)
       {
           itemList = new ArrayList<>();
       }

       if(action!=null)
       {
           if(action.equals("register"))
           {
               if(username.equals(""))
               {
                   request.setAttribute("message", "Please enter a a username");
                   getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
               }
               else
               {
                   session.setAttribute("username", username);
                   response.sendRedirect("ShoppingList");
               } 
            }
           //add
           else if(action.equals("add"))
           {
               String item = request.getParameter("item");
               if(item.equals("")){
                   request.setAttribute("message", "Please enter an item");
                   getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
               }
               else
               {
                   itemList.add(item);
                   session.setAttribute("itemlist", itemList);
                   response.sendRedirect("ShoppingList");
               }
            }
           //delete
           else if(action.equals("delete"))
           {
               int index = Integer.parseInt(request.getParameter("itemname"));
               itemList.remove(index);
               session.setAttribute("itemlist", itemList);
               response.sendRedirect("ShoppingList");
           }          
       }
    }
}
