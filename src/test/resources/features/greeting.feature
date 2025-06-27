Feature: Greeting page

  Scenario: Greeting a user
    Given the user opens the greeting page
    When the user enters the name "Codex" and submits
    Then the page shows the greeting for "Codex"
