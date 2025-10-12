<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = htmlspecialchars($_POST["name"]);
    $email = htmlspecialchars($_POST["email"]);
    $message = htmlspecialchars($_POST["message"]);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Submission Success</title>
</head>
<body>
    <h1>Thank you, <?php echo $name; ?>!</h1>
    <p>Your message has been received successfully.</p>
    <a href="contact.html">Back to form</a>
</body>
</html>
<?php
} else {
    header("Location: contact.html");
    exit();
}
?>
