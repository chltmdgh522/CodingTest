=begin
1. 입력을 리스트에서 받기 
2. Y-2 F-3 O-4
3. 리스트에서 값 중복 제거 
4. 각 게임에서 해당 인원수를 나누고 나머지는 버린다.
=end

input = gets.chomp.split.map(&:to_s)
N = input[0].to_i
game = input[1]
name = []

N.times do |i|
  name[i] = gets.chomp.to_s
end

name_size = name.uniq.size

case game
when "Y"
  puts name_size
when "F"
  puts name_size / 2
when "O"
  puts name_size / 3
end
