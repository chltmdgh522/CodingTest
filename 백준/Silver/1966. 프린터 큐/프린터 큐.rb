T = gets.chomp.to_i
T.times do |i|
  # 입력
  input = gets.chomp.split.map(&:to_i)
  n = input[0]
  m = input[1]
  document = gets.chomp.split.map(&:to_i)

  queue = []
  document.each.with_index do |num, j|
    queue.push([num, j])  # 배열을 직접 생성
  end

  answer = []
  while !queue.empty?
    pData = queue.first
    max_priority = queue.map { |q| q[0] }.max  # 현재 큐에서 가장 높은 중요도 찾기

    if pData[0] < max_priority
      queue.push(queue.shift)  # 중요도가 낮으면 뒤로 보냄
    else
      answer.push(queue.shift)  # 중요도가 가장 크면 처리
    end
  end

  # 출력 (몇 번째로 출력되는지 찾기)
  answer.each.with_index do |data_ex, k|
    if data_ex[1] == m
      puts k + 1
      break
    end
  end
end
