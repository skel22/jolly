@extends('layout')

@section('content')
<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <!-- Your existing head content here -->

    <style>
        /* Your existing styles here */

        body {
            font-family: 'Figtree', sans-serif;
        }

        .center-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            text-align: center;
        }

        .navbar {
            /* Your navbar styles go here */
        }

        .content-container {
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            align-items: center;
            margin-top: 20px; /* Adjust as needed */
        }

        .text-container {
            max-width: 600px; /* Adjust as needed */
            text-align: left; /* Align text to the left */
            margin-right: 20px; /* Add margin to separate text and image */
        }

        .image-container {
            flex-shrink: 0; /* Prevent image from shrinking */
        }

        .image-container img {
            width: 100%; /* Make sure the image takes the full width */
            max-width: 400px; /* Set a maximum width for the image */
            height: auto; /* Maintain the aspect ratio */
        }

        nav {
            z-index: 40;
        }
    </style>


</head>
<body class="antialiased bg-gray-100 dark:bg-gray-900">
    <div class="navbar">
    </div>

    <x-main-page/>

    <section class="justify-center p-5 my-8">
    <div class="p-8 overflow-hidden w-full">
        <div>
            <h1 class="text-4xl font-bold text-center my-5 w-">About us</h1>
        </div>
        <x-about-us/>
    </div>
</section>

</body>
</html>
@endsection
