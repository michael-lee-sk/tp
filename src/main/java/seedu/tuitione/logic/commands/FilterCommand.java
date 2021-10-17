package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedGrade;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.StudentIsOfSpecifiedGrade;

/**
 * Filters out students and lessons in tuitione book whose grade is equal to the specified grade.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters out students and lessons if they are"
            + " of the specified grade.\n"
            + "Parameters: GRADE \n"
            + "Example: " + COMMAND_WORD + " S2";

    private final Grade grade;

    public FilterCommand(Grade grade) {
        this.grade = grade;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(new StudentIsOfSpecifiedGrade(grade));
        model.updateFilteredLessonList(new LessonIsOfSpecifiedGrade(grade));
        String output = String.format(Messages.MESSAGE_STUDENTS_FOUND_OVERVIEW,
                model.getFilteredStudentList().size())
                    + "\n"
                    + String.format(Messages.MESSAGE_LESSON_FOUND_OVERVIEW,
                    model.getFilteredLessonList().size());

        return new CommandResult(output);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && grade.equals(((FilterCommand) other).grade)); // state check
    }
}