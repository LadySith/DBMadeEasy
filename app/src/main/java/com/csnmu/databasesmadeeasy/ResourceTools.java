package com.csnmu.databasesmadeeasy;

import android.content.Context;
import android.content.res.Resources;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class ResourceTools {

    /**
     * Queries resource manager and returns the int identifier for a named resource. If no resource is found, -1 is returned.
     * @param context context
     * @param resourceType resource folder e.g. drawable, raw, etc
     * @param resourceName resource name. The suffix of the resource identifier e.g. R.raw.filename where filename is the resource name
     * @return integer id of resource
     */
    public static int getResourceId(Context context, String resourceType, String resourceName) {
        if (context == null) {
            return -1;
        }

        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, resourceType, context.getPackageName());
    }

    public static List<Concept> getConcepts(Context context){
        List<Concept> conceptList = new ArrayList<>();

        // Get all concepts
        NodeList concepts = XMLTools.readAll(context, R.raw.concepts, "//Concept");

        // Read each concept, processing its children
        for (int i = 0; i < concepts.getLength(); i++) {
            Node item = concepts.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE){
                Concept concept = new Concept();

                // Load Basic concept attributes
                concept.setConceptTitle(item.getAttributes().getNamedItem("title").getNodeValue());
                concept.setConceptSummary(item.getAttributes().getNamedItem("summary").getNodeValue());

                // Get video
                Node videoName = item.getAttributes().getNamedItem("videoId");
                if (videoName != null) {
                    concept.setVideoRawResId(getResourceId(context, "raw",videoName.getNodeValue()));
                }

                NodeList pages = XMLTools.readAll(context, R.raw.concepts, "//Concept[" + (i + 1) + "]//Page");
                concept.setConceptPages(populatePages(context, pages));

                NodeList quizzes = XMLTools.readAll(context, R.raw.concepts, "//Concept[" + (i + 1) + "]//Quiz");
                concept.setQuizList(populateQuizzes(context, quizzes, i+1));
                conceptList.add(concept);
            }
        }

        return conceptList;
    }

    /**
     * Processes all quiz'es of a quizzes node
     * @param quizzes nodeset of quiz nodes
     * @param conceptId Current XPath Concept ID for use in XPath Quer
     * @return list of Quiz objects
     */
    private static List<Quiz> populateQuizzes(Context context, NodeList quizzes, int conceptId) {
        List<Quiz> quizList = new ArrayList<>();

        // Create Quiz objects
        for (int i = 0; i < quizzes.getLength(); i++) {
            Node item = quizzes.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Quiz quiz = new Quiz();
                quiz.setQuestion(item.getAttributes().getNamedItem("question").getNodeValue());
                Node imgResId = item.getAttributes().getNamedItem("imgResId");
                if (imgResId != null)
                    quiz.setImgResId(getResourceId(context, "drawable", imgResId.getNodeValue()));

                // XPath Query to get Position of solution in each answer node set.
                String xmlQuery = "count(//Concept[" + conceptId + "]//Quiz[" + (i+1) + "]//Answer[@index=ancestor::Answers/@solution]/preceding::Answer) - count(//Concept[" + conceptId + "]//Quiz[" + (i+1) + "]//Answer[@index=ancestor::Answers/@solution]/preceding::Answers/Answer)";
                quiz.setSolution(XMLTools.aggregateQuery(context, R.raw.concepts, xmlQuery));

                // Populate all answers for a quiz
                NodeList answers = XMLTools.readAll(context, R.raw.concepts, "//Concept[" + conceptId + "]//Quiz[" + (i+1) + "]//Answer");
                quiz.setAnswers(populateAnswers(answers));
                quizList.add(quiz);
            }
        }

        return quizList;
    }

    /**
     * Processes all answers of a quiz node
     * @param answers nodeset of answer nodes
     * @return List of answer strings
     */
    private static List<String> populateAnswers(NodeList answers) {
        List<String> answerList = new ArrayList<>();

        // Create Answer Strings
        for (int i = 0; i < answers.getLength(); i++) {
            Node item = answers.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                answerList.add(item.getTextContent());
            }
        }

        return answerList;
    }

    /**
     * Processes all pages of a concept node
     * @param pages nodeset of page nodes
     * @return List of instantiated Page objects
     */
    private static List<Page> populatePages(Context context, NodeList pages) {
        List<Page> pageList = new ArrayList<>();

        // Create Page objects
        for (int i = 0; i < pages.getLength(); i++) {
            Node item = pages.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Page page = new Page();
                page.setPageTitle(item.getAttributes().getNamedItem("title").getNodeValue());

                // Get image
                Node imgId = item.getAttributes().getNamedItem("imgId");
                if (imgId != null) {
                    page.setImage(getResourceId(context, "drawable",imgId.getNodeValue()));
                }

                page.setPageText(item.getTextContent());
                pageList.add(page);
            }
        }

        return pageList;
    }
}
