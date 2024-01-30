<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Jolly Dashboard - Self Service for Ice Cream Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="{{ asset('css/app.css') }}">

</head>
<body>   
    <style>
        body {
            background-color: #f4f4f4;
            font-family: 'Arial', sans-serif;
        }

        .navbar {
            background-color: #F3E5AB;
            padding: 15px;
        }

        .navbar-list {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .navbar-item {
            display: inline-block;
            margin-left: 5px;
            margin-top: 5px;
            margin-right: 15px;
        }

        .navbar-link {
            background: linear-gradient(45deg, #FF69B4, #FF1493);
            color: #FFFFFF;
            padding: 8px 12px; /* Add padding for better aesthetics */
            border-radius: 5px; /* Add border-radius for rounded corners */
            text-decoration: none; /* Remove default underline */
        }

        .navbar-link:hover {
            transform: scale(1.1);
            background: linear-gradient(45deg, #FF69B4, #FF1493);
        }
    </style>

    <nav class="navbar">
        <ul class="navbar-list">
            <li class="navbar-item"><a href="/admin/jolly" class="navbar-link">Home</a></li>
            <li class="navbar-item"><a href="/admin/dashboard" class="navbar-link">Products</a></li>
        </ul>
    </nav>

    <div>
        @yield('content')
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>
