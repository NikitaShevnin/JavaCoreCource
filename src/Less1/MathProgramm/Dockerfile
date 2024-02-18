FROM openjdk:latest
LABEL authors="ВТБ"

# Установка рабочей директории для контейнера
WORKDIR ./

# копирование исходного кода в контейнер
COPY ./ /app

# Команда для сборки и запуска приложения
CMD java -cp /app/ui MathProgView
