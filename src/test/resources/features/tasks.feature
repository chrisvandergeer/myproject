Feature: Tasks page

  Scenario: Adding a task
    Given the user opens the tasks page
    When the user adds a task named "Test" with description "Beschrijving" and date "2030-01-01"
    Then the task list shows a task named "Test"
