=begin
1. 2차원 리스트를 입력을 받는다.
2. 순서대로 3개씩 더해서 max를 해서 구한다.
3. 이떄 가장 큰 max의 자리수를 알아서 출력한다.
=end
N = gets.chomp.to_i
result = []

def number(data)
  max = 0
  for j in 0...3
    for k in j + 1...4
      for l in k + 1...5
        sum = data[j] + data[k] + data[l]
        one_place = sum.to_s
        if one_place.length > 1
          max = [one_place[1].to_i, max].max
        else
          max = [one_place.to_i, max].max
        end

      end
    end
  end
  return max
end

# 입력 및 로직 실행
N.times do |i|
  data = gets.chomp.split.map(&:to_i)
  answer = number(data)
  result[i] = answer
end

compare = 0
place = 0
# 출력
N.times do |i|
  if compare <= result[i]
    place = i + 1
    compare = result[i]
  end
end

puts place