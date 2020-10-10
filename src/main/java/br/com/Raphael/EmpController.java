package br.com.Raphael;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmpController {
    @Autowired
    EmpDao dao;//will inject dao from XML file

    /* Página inicial do Web App */
    @RequestMapping("/") 
    public String startApp(){
        return "index"; 
    }
    
    /* Ir para homepage */
    @RequestMapping("/index") 
    public String home(){
        return "index"; 
    }
    
    /*Mostra a página de formulário para adicionar novos empregados
     */
    @RequestMapping("/empform")
    public String showform(Model m){
        m.addAttribute("command", new Emp());
        return "empform";
    }
    /*Salva novo objeto EMPREGADO no banco de dados, e em seguida redireciona para a página da Lista de Empregados*/
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("emp") Emp emp){
        dao.save(emp);
        return "redirect:/viewemp";
    }
    /* Retorna a lista de empregados e os apresenta na página da Lista de Empregados */
    @RequestMapping("/viewemp")
    public String viewemp(Model m){
        List<Emp> list=dao.getEmployees();
        m.addAttribute("list",list);
        return "viewemp";
    }
    /* Mostra o formulário de edição para o ID do Empregado solicitado.
     * A @PathVariable coloca o ID da URL dentro da variável.*/
    @RequestMapping(value="/editemp/{id}")
    public String edit(@PathVariable int id, Model m){
        Emp emp=dao.getEmpById(id);
        m.addAttribute("command",emp);
        return "empeditform";
    }
    /* Atualiza o objeto. */
    @RequestMapping(value="/editsave",method = RequestMethod.POST)
    public String editsave(@ModelAttribute("emp") Emp emp){
        dao.update(emp);
        return "redirect:/viewemp";
    }
    /* Deleta o registro para a id da URL e redireciona para /viewemp */
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        dao.delete(id);
        return "redirect:/viewemp";
    }
}
