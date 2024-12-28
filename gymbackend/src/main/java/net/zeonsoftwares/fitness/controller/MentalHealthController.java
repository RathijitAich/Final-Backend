package net.zeonsoftwares.fitness.controller;



import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/mentalhealth")
public class MentalHealthController {

    @PostMapping("/analyze")
    public String analyzeResponses(@RequestBody List<QuestionAndAnswer> questionAndAnswers) {

        int stressScore = 0;

        for (QuestionAndAnswer qa : questionAndAnswers) {
            String question = qa.getQuestion();
            String answer = qa.getAnswer(); // For sliders, "answer" will be numeric as a string.

            // Yes/No Questions
            if (question.equals("Do you experience frequent mood swings?")) {
                if (answer.equalsIgnoreCase("Yes")) {
                    stressScore += 2;
                }
            } else if (question.equals("Do you feel overwhelmed by emotions or situations regularly?")) {
                if (answer.equalsIgnoreCase("Yes")) {
                    stressScore += 3;
                }
            } else if (question.equals("Have you lost interest in activities you used to enjoy?")) {
                if (answer.equalsIgnoreCase("Yes")) {
                    stressScore += 2;
                }
            } else if (question.equals("Are there specific events or situations that trigger your anxiety?")) {
                if (answer.equalsIgnoreCase("Yes")) {
                    stressScore += 3;
                }
            } else if (question.equals("Do you feel like you can manage your stress effectively?")) {
                if (answer.equalsIgnoreCase("No")) {
                    stressScore += 4;
                }
            } else if (question.equals(
                    "Have you noticed any physical symptoms, like headaches or fatigue, when you're stressed?")) {
                if (answer.equalsIgnoreCase("Yes")) {
                    stressScore += 3;
                }
            } else if (question.equals("Do you usually get enough sleep at night?")) {
                if (answer.equalsIgnoreCase("No")) {
                    stressScore += 3;
                }
            } else if (question.equals("Do you have someone you trust to talk to about personal matters?")) {
                if (answer.equalsIgnoreCase("No")) {
                    stressScore += 2;
                }
            } else if (question.equals("Do you feel fulfilled by your work, studies, or daily activities?")) {
                if (answer.equalsIgnoreCase("No")) {
                    stressScore += 2;
                }
            } else if (question.equals("Do you believe your physical health impacts your mental well-being?")) {
                if (answer.equalsIgnoreCase("Yes")) {
                    stressScore += 1;
                }
            } else if (question.equals("Do you often find yourself ruminating on past events or future worries?")) {
                if (answer.equalsIgnoreCase("Yes")) {
                    stressScore += 3;
                }
            } else if (question.equals("Do you have activities or hobbies that help you relax?")) {
                if (answer.equalsIgnoreCase("No")) {
                    stressScore += 3;
                }
            }

            // Slider Questions
            else if (question.equals("How often do you feel happy or content in your daily life?")) {
                stressScore += (5 - Integer.parseInt(answer)); // Inverting to decrease stress with higher
                                                               // happiness.
            } else if (question.equals("How often do you feel stressed or anxious?")) {
                stressScore += Integer.parseInt(answer); // Higher value means more stress.
            } else if (question.equals("How often do you wake up feeling refreshed and rested?")) {
                stressScore += (5 - Integer.parseInt(answer)); // Inverted to add stress for less rest.
            } else if (question.equals("How often do you feel lonely or isolated?")) {
                stressScore += Integer.parseInt(answer);
            } else if (question.equals("How satisfied are you with your current social relationships?")) {
                stressScore += (5 - Integer.parseInt(answer)); // Inverted for reduced satisfaction adding stress.
            } else if (question.equals("How often do you feel overburdened by your responsibilities?")) {
                stressScore += Integer.parseInt(answer);
            } else if (question.equals("How often do you engage in physical activities or exercise?")) {
                stressScore += (5 - Integer.parseInt(answer)); // Inverted: more exercise lowers stress.
            } else if (question.equals("Do you feel hopeful and optimistic about your future?")) {
                stressScore += (5 - Integer.parseInt(answer)); // Inverted to indicate reduced stress with optimism.
            }
        }

        // Final Feedback Based on Stress Score
        String feedback;
        if (stressScore < 20) {
            feedback = "Stress Score is " + stressScore
                    + ". Your stress level is low. Keep maintaining a healthy balance! Your responses suggest strong coping mechanisms for: "
                    + "restful sleep, positive social relationships, and a manageable workload.";
        } else if (stressScore < 40) {
            feedback = "Stress Score is " + stressScore
                    + ". Your stress level is moderate. You may benefit from relaxation techniques like mindfulness or exercise. "
                    + "Consider addressing areas where stress frequently arises, such as emotional stability or social support.";
        } else {
            feedback = "Stress Score is " + stressScore
                    + ". Your stress level is high. Seek support or professional advice. Focus on improving rest, reducing triggers, "
                    + "and finding ways to engage in activities that bring joy and relaxation.";
        }

        // Return the feedback to the frontend in JSON format
        return feedback;

    }

    // Define a nested class to hold question and answer data
    // List<QuestionAndAnswer> means that the data will be in the form of a list of
    // QuestionAndAnswer objects
    // list is the data structure that holds the data , it is like vector in c++
    public static class QuestionAndAnswer {
        private String question;
        private String answer;

        // Getters and setters
        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        @Override
        public String toString() {
            return "QuestionAndAnswer{" +
                    "question='" + question + '\'' +
                    ", answer='" + answer + '\'' +
                    '}';
        }
    }
}
