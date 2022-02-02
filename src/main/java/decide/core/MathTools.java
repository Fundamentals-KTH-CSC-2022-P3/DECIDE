package decide.core;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MathTools {
    public static boolean pointsAreCoveredByCircle(Point p1, Point p2, Point p3, double radius){
        if (p1.equals(p2) && p1.equals(p3) && p2.equals(p3)) {
            return true;
        }
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            Point coincide = null;      // Represents the point where two points coincide.
            Point remaining = null;     // Represents the other remaining point that does not coincide.

            // Figure out which two points coincide.
            if (p1.equals(p2)) {
                coincide = p1;
                remaining = p3;
            }
            else if (p1.equals(p3)) {
                coincide = p1;
                remaining = p2;
            } else {
                coincide = p2;
                remaining = p1;
            }

            // Calculate the midpoint, which is the point exactly between the 'coincide' point and the 'remaining' point.
            Point midpoint = new Point((coincide.x + remaining.x) / 2, (coincide.y + remaining.y) / 2);

            return midpoint.distance(coincide) <= radius && midpoint.distance(remaining) <= radius;
        }

        Optional<Boolean> isCoveredByAtLeastOneCircle = calculateAllCircleFocusAndVerifierPointPairs(p1, p2, p3, radius)
                .stream()
                .map(p -> Point.euclidianDistanceBetween(p.getFirst(), p.getSecond()) <= radius)
                .reduce((a, b) -> a||b);
        if (isCoveredByAtLeastOneCircle.orElse(false)) {
            return true;
        }

        return false;
    }

    private static List<Point.Pair> calculateAllCircleFocusAndVerifierPointPairs(Point p1, Point p2, Point p3, double radius) {
        // The Circle focuses are computed from 2 points and then paired with the 3rd
        List<Point.Pair> toBeReturned = calculateTwoFocusesOfCirclesPassingThrough(p1, p2, radius).stream()
                .map((Point p) -> new Point.Pair(p, p3)).collect(Collectors.toList());
        toBeReturned.addAll(calculateTwoFocusesOfCirclesPassingThrough(p2, p3, radius).stream()
                .map((Point p) -> new Point.Pair(p, p1)).collect(Collectors.toList()));
        toBeReturned.addAll(calculateTwoFocusesOfCirclesPassingThrough(p3, p1, radius).stream()
                .map((Point p) -> new Point.Pair(p, p2)).collect(Collectors.toList()));
        return toBeReturned;
    }

    private static List<Point> calculateTwoFocusesOfCirclesPassingThrough(Point x, Point y, double radius) {
        // The mathematics in this method are calculated from a
        // Link: https://web.archive.org/web/20141008055635/http://mathforum.org/library/drmath/view/53027.html
        double distanceBetweenXY = Point.euclidianDistanceBetween(x, y);
        Point pointBetweenXY = Point.createPointBetween(x, y);

        // Calculate the normalized vector perpendicular to the  line between X and Y as the circle focuses will
        // be on the line defined by it.
        double[] normVector = calculateNormalizedVectorPerpendicularToLineBetween(x, y);

        // Using Pythagoras since we now have a fair triangle
        double distanceFromPointBetweenXYToFocus = sqrt(pow(radius, 2) - pow(distanceBetweenXY/2, 2));

        return calculateTwoCircleFocusesOnLine(pointBetweenXY, distanceFromPointBetweenXYToFocus, normVector);

    }

    private static List<Point> calculateTwoCircleFocusesOnLine(Point point, double distanceFromPointToFocus, double[] normalizedVector) {
        return Arrays.asList(
                new Point(
                        point.x + distanceFromPointToFocus * normalizedVector[0],
                        point.y + distanceFromPointToFocus * normalizedVector[1]
                ),
                new Point(
                        point.x - distanceFromPointToFocus * normalizedVector[0],
                        point.y - distanceFromPointToFocus * normalizedVector[1]
                )
        );
    }

    private static double[] calculateNormalizedVectorPerpendicularToLineBetween(Point x, Point y) {
        double distanceBetweenXY = Point.euclidianDistanceBetween(x, y);
        return new double[]{(x.y-y.y)/distanceBetweenXY, (y.x-x.x)/distanceBetweenXY};
    }
}
