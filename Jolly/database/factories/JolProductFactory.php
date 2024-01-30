<?php

namespace Database\Factories;

use App\Models\JolProduct;
use Illuminate\Database\Eloquent\Factories\Factory;

class JolProductFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'name' => $this->faker->word,
            'price' => $this->faker->randomFloat(2, 1, 100),
            'detail' => $this->faker->paragraph,
            'image' => 'default_image.jpg', // You can customize this based on your setup
        ];
    }
}
