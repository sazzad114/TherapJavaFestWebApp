package net.therap.util;

import net.therap.domain.question.Question;
import net.therap.domain.question.QuestionBank;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.contexts.ServletLifecycle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 10:38 AM
 */

@Name("questionUtil")
@Scope(ScopeType.APPLICATION)
@AutoCreate
public class QuestionUtil{

    @Out(value = "questionBank",scope = ScopeType.APPLICATION)
    private QuestionBank questionBank;


    private static final String QUESTIONBANK_XML = "questionbank.xml";

    @Factory(value = "questionBank")
    public void getQuestionBank() throws Exception{

        JAXBContext questionBankContext = JAXBContext.newInstance(QuestionBank.class);
        Unmarshaller unmarshaller = questionBankContext.createUnmarshaller();
        FileReader fileReader = new FileReader(ServletLifecycle.getServletContext().getRealPath("/WEB-INF/classes/questionbank.xml"));
        questionBank = (QuestionBank) unmarshaller.unmarshal(fileReader);

    }
}
