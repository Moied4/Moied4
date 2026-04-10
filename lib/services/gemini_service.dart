import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter_dotenv/flutter_dotenv.dart';

class GeminiService {
  final String _apiKey = dotenv.env['API_KEY'] ?? "";

  Future<String> getRecipe(String ingredients) async {
    final String url =
    "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash:generateContent?key=$_apiKey";

    final response = await http.post(
      Uri.parse(url),
      headers: {
        "Content-Type": "application/json",
      },
      body: jsonEncode({
        "contents": [
          {
            "parts": [
              {
                "text":
                    "I have these ingredients: $ingredients. Suggest a recipe."
              }
            ]
          }
        ]
      }),
    );

    if (response.statusCode == 200) {
      final data = jsonDecode(response.body);
      return data['candidates'][0]['content']['parts'][0]['text'];
    } else {
      print("API Error Body: ${response.body}");
      return "Error: ${response.statusCode}";
    }
  }
}