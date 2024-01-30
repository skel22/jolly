<?php

use App\Http\Controllers\Api\JolProductAPIController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::apiResource('jolproducts', JolProductAPIController::class)->except([
    'create', 'edit'
]);


Route::prefix('api')->group(function () {
    Route::get('/jolproducts', [JolProductAPIController::class, 'index']);
    Route::get('/jolproducts/{id}', [JolProductAPIController::class, 'show']);
});
