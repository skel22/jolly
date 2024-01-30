@extends('layout')

@section('content')
<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
     <link rel="preconnect" href="https://fonts.bunny.net">
    <link href="https://fonts.bunny.net/css?family=figtree:400,600&display=swap" rel="stylesheet"/>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap" rel="stylesheet">

    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">

    <script src="//unpkg.com/alpinejs" defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.tiny.cloud/1/xjei76kbizfjo1f2974ozkdl8tkdjgb2jrmi8pvkjphg2uob/tinymce/6/tinymce.min.js"
            referrerpolicy="origin"></script>

    <script src="//unpkg.com/alpinejs" defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js"></script>
    <script src="https://cdn.tiny.cloud/1/xjei76kbizfjo1f2974ozkdl8tkdjgb2jrmi8pvkjphg2uob/tinymce/6/tinymce.min.js"
            referrerpolicy="origin"></script>

<head>
    <style>

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

        }

        .content-container {
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            align-items: center;
            margin-top: 20px; 
        }

        .text-container {
            max-width: 600px; 
            text-align: left; 
            margin-right: 20px; 
        }

        .image-container {
            flex-shrink: 0;
        }

        .image-container img {
            width: 100%; 
            max-width: 400px; 
            height: auto; 
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

<x-footer/>

</body>
</html>
@endsection
